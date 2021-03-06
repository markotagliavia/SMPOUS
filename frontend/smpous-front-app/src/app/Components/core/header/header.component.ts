import { Component, OnInit, Injectable, Input, OnChanges, SimpleChanges, Output,EventEmitter } from '@angular/core';
import { AuthService } from '../../../Services/auth.service';
import { HttpService } from '../../../Services/http-service.service';
import {
  Router,
  ActivatedRoute
} from '@angular/router'; 

@Injectable()
@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit,OnChanges {

  @Input() childMessage: string;
  @Output() messageEvent = new EventEmitter<string>();
  client : boolean;
  clientOnly : boolean;
  manager : boolean;
  admin : boolean;

  constructor(private authService: AuthService, private router: Router, private httpService: HttpService) {
    this.client = false;
    this.manager = false;
    this.admin = false;
    this.clientOnly = false;
   }

  ngOnInit() {
    this.refreshView();
  }

 
  ngOnChanges(changes: SimpleChanges) {
      if(changes['childMessage'])
      {
        if(this.childMessage != 'empty')
        {
          //alert("RADIIIIIIII");
         this.refreshView();
         //this.childMessage = 'empty';
        }
      }
  }




  refreshView()
  {
    this.client = false;
    this.manager = false;
    this.admin = false;
    if(this.authService.currentUserName() != undefined)
    {
        if(this.authService.currentUserName().length > 0)
        {
            if(this.authService.isLoggedInRole('Admin'))
            {
              this.admin = true;
              this.client = true;                              
            }
            else if(this.authService.isLoggedInRole('AppUser'))
            {
              this.client = true;
              this.clientOnly = true;
            }
            else
            {
              this.clientOnly = true;
            }
        }
        else
        {
          this.clientOnly = true;
        }
    }
    else
    {
      this.clientOnly = true;
    }
  }

  logOut()
  {
    this.httpService.logOut(this.authService.currentUserName()).subscribe
    (
          (res: any) => {
             
            this.authService.logOut();//heade
            this.router.navigate(['home/login']);
            this.refreshView();
            window.location.reload();
          },
          error =>{

            alert(error.json().Message); 
                
        }
      
      
      )
    
  }

  

}

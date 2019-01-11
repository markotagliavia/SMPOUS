import { Component, OnInit } from '@angular/core';
import { ServiceManager } from '../../../Services/[services].service';
import { AuthService } from '../../../Services/auth.service';
import { TypeOfVehicle } from '../../../Model/type-of-vehicle';
import { Service } from '../../../Model/service';    
import { Theater } from '../../../Model/theather';

@Component({
  selector: 'app-services-main',
  templateUrl: './services-main.component.html',
  styleUrls: ['./services-main.component.css']
})
export class ServicesMainComponent implements OnInit {

  manager : boolean;
  client : boolean;
  admin : boolean;
  smeDaKreira : boolean;
  theathers : Theater[];

  constructor(public serviceManager: ServiceManager,private authService: AuthService) { 
    this.client = false;
    this.manager = false;
    this.admin = false;
    this.smeDaKreira = true;
    this.theathers = []; //to do uraditi zahtev za dobijanje...
    this.serviceManager.getTheathers().subscribe(
      (res: any) => {
               
              for(let i=0; i<res.length; i++){
                this.theathers.push(res[i]); //use i instead of 0
            }     
      },
      error =>{
          console.log(error);
      }
      
    )
  }

  ngOnInit() {
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
            }
        }
    }
  }

}

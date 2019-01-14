import { Component, OnInit } from '@angular/core';
import { ServiceManager } from '../../../Services/[services].service';
import { AuthService } from '../../../Services/auth.service';
import { TypeOfVehicle } from '../../../Model/type-of-vehicle';
import { Service } from '../../../Model/service';    
import { Theater } from '../../../Model/theather';
import { Cinema } from '../../../Model/cinema';

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
  cinemas : Cinema[];
  nameInput : string;
  radiusInput : number;
  isSortInput : boolean;
  x:number;
  y:number;

  constructor(public serviceManager: ServiceManager,private authService: AuthService) { 
    this.client = false;
    this.manager = false;
    this.admin = false;
    this.smeDaKreira = true;
    this.nameInput = "";
    this.isSortInput = false;
    this.radiusInput = 999999999;
    this.x = 0;
    this.y = 0;
    this.search();
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

  search()
  {
    this.cinemas = []; //to do uraditi zahtev za dobijanje...
    var nameParam = "";
    if(this.nameInput == "")
    {
      nameParam = "*";
    }
    else 
    {
      nameParam = this.nameInput;
    }
    this.serviceManager.getCinemaWithFilter(this.authService.currentUserUsername(), nameParam, this.x, this.y, this.radiusInput, this.isSortInput).subscribe(
      (res: any) => {
               console.log(res);
              for(let i=0; i<res.length; i++){
                this.cinemas.push(res[i]); //use i instead of 0
            }     
      },
      error =>{
          console.log(error);
      }
      
    )

  }

}

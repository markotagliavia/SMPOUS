import { Component, OnInit } from '@angular/core';
import { HttpService } from '../../../Services/http-service.service';
import { AuthService } from '../../../Services/auth.service'; 
import { CurrentUser } from '../../../Model/current-user'; 

@Component({
  selector: 'app-manager-panel-main',
  templateUrl: './manager-panel-main.component.html',
  styleUrls: ['./manager-panel-main.component.css']
})
export class ManagerPanelMainComponent implements OnInit {

  appUsers : CurrentUser[];
  usernameInput : string;
  firstNameInput : string;
  lastNameInput : string;
  radiusInput : number;
  x : number;
  y : number;

  constructor(public httpService: HttpService,private authService: AuthService) {
    this.appUsers = [];
    this.usernameInput = "";
    this.firstNameInput = "";
    this.lastNameInput = "";
    this.radiusInput = 999999999;
    this.x = 0;
    this.y = 0;
    this.httpService.getAllAppUsers(this.authService.currentUserUsername()).subscribe(
      (res: any) => {
               console.log(res);
              for(let i=0; i<res.length; i++){

                let pomUser: CurrentUser = res[i];
                pomUser.latitude = res[i].x;
                pomUser.longitude = res[i].y;
                pomUser.surname = res[i].lastname;
                pomUser.role = res[i].typeOfUser;
                pomUser.approved = res[i].isActive;
                if(pomUser.role != 'admin' && pomUser.approved == true)
                {
                  this.appUsers.push(pomUser); //use i instead of 0
                }
            }     
      },
      error =>{
          console.log(error);
          window.alert(error);
      });
   }

   search()
   {
    this.appUsers = [];
     var usernameParam = "";
     var firstNameParam = "";
     var lastNameParam = "";
     if(this.usernameInput == "")
     {
       usernameParam = "*";
     }
     else 
     {
       usernameParam = this.usernameInput;
     }
     if(this.firstNameInput == "")
     {
       firstNameParam = "*";
     }
     else 
     {
       firstNameParam = this.firstNameInput;
     }
     if(this.lastNameInput == "")
     {
       lastNameParam = "*";
     }
     else 
     {
       lastNameParam = this.lastNameInput;
     }
     console.log(this.authService.currentUserUsername(), usernameParam, firstNameParam, lastNameParam, true, this.radiusInput, this.x, this.y);

     this.httpService.getAllAppUsersWithFilter(this.authService.currentUserUsername(), usernameParam, firstNameParam, lastNameParam, true, this.radiusInput, this.x, this.y).subscribe(
      (res: any) => {
               console.log(res);
              for(let i=0; i<res.length; i++){

                let pomUser: CurrentUser = res[i];
                pomUser.latitude = res[i].x;
                pomUser.longitude = res[i].y;
                pomUser.surname = res[i].lastname;
                pomUser.role = res[i].typeOfUser;
                pomUser.approved = res[i].isActive;
                if(pomUser.role != 'admin' && pomUser.approved == true)
                {
                  this.appUsers.push(pomUser); //use i instead of 0
                }
            }     
      },
      error =>{
          console.log(error);
          window.alert(error);
      });
   }

  ngOnInit() {
  }

}

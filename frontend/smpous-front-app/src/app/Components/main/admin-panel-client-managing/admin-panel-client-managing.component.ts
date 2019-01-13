import { Component, OnInit } from '@angular/core';
import { HttpService } from '../../../Services/http-service.service';
import { AuthService } from '../../../Services/auth.service'; 
import { CurrentUser } from '../../../Model/current-user'; 

@Component({
  selector: 'app-admin-panel-client-managing',
  templateUrl: './admin-panel-client-managing.component.html',
  styleUrls: ['./admin-panel-client-managing.component.css']
})
export class AdminPanelClientManagingComponent implements OnInit {

  appUsers : CurrentUser[];
  usernameInput : string;
  firstNameInput : string;
  lastNameInput : string;
  radiusInput : number;
  isActiveInput : boolean;
  x:number;
  y:number;

  constructor(public httpService: HttpService,private authService: AuthService) { 
    this.appUsers = [];
    this.usernameInput = "";
    this.firstNameInput = "";
    this.lastNameInput = "";
    this.radiusInput = 0;
    this.isActiveInput = true;
    this.radiusInput = 999999999;
    this.x = 0;
    this.y = 0;
    this.search();
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
    this.httpService.getAllAppUsersWithFilter(this.authService.currentUserUsername(), usernameParam, firstNameParam, lastNameParam, this.isActiveInput, this.radiusInput, this.x, this.y).subscribe(
      (res: any) => {
               console.log(res);
              for(let i=0; i<res.length; i++){

                let pomUser: CurrentUser = res[i];
                pomUser.latitude = res[i].x;
                pomUser.longitude = res[i].y;
                pomUser.surname = res[i].lastname;
                pomUser.role = res[i].typeOfUser;
                if(pomUser.role != 'admin')
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

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

  constructor(public httpService: HttpService,private authService: AuthService) { 
    this.appUsers = [];
    this.httpService.getAllAppUsers(this.authService.currentUserUsername()).subscribe(
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

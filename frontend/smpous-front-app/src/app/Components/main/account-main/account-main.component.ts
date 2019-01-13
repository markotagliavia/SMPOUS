import { Component, OnInit } from '@angular/core';
import { User } from '../../../Model/user';
import { IdentityUser } from '../../../Model/identity-user';
import { CurrentUser} from '../../../Model/current-user';
import { HttpService } from '../../../Services/http-service.service'; 
import {AuthService } from '../../../Services/auth.service'
import { ServiceManager} from '../../../Services/[services].service'
import { takeUntil } from 'rxjs/operators';
import {
  Router,
  ActivatedRoute
} from '@angular/router';

@Component({
  selector: 'app-account-main',
  templateUrl: './account-main.component.html',
  styleUrls: ['./account-main.component.css']
})
export class AccountMainComponent implements OnInit {

  regUser : IdentityUser;
  errorTextLogin : string;
  errorTextReg : string;


  constructor(private http: HttpService, private authService: AuthService, private router: Router, private serviceManager : ServiceManager) {
    //to do posalji zahtev za sve rez koje su vezane za ovog korisnika na sesiji

    this.regUser = {
      'username' :  this.authService.currentUser().username,
      'confirmPassword' : '',
      'password' : '',
      'name' : this.authService.currentUserFirstName(),
      'surname' : this.authService.currentUserSurname(),
      'birth' : this.authService.currentUserBirth(),
      'street':this.authService.currentUserAdresa(),
      'number' : this.authService.currentUserNumber(),
      'latitude' : this.authService.currentUserLatitude(),
      'longitude' :this.authService.currentUserLongitude()
    }

    this.errorTextLogin = '';
    this.errorTextReg = '';
   }

  ngOnInit() {
  }

  changeLogin()
  {
    if(this.regUser.password.length == 0 || this.regUser.confirmPassword.length == 0){
      this.errorTextLogin = "All fields are required";
      return false;
    }
    else
    {
      if(this.regUser.password.length < 5)
      {
        this.errorTextLogin = "Password must have minimum 6 characters";
        return false;
      }
      else
      {
        if(this.regUser.confirmPassword != this.regUser.password)
        {
          this.errorTextLogin = "Password must be the same";
          return false;
        }

        this.authService.changePassword(this.authService.currentUserUsername(), this.authService.currentUserPassword(),this.regUser.password,this.regUser.confirmPassword).subscribe(
            (res : any) =>
            {
              if(res._body != "")
              {
                  let data = res.json();
                  console.log(data);
                  if(data)  
                  {
                        // console.log(res);
                        let currentUser: CurrentUser;
                        
                        currentUser = new CurrentUser(true,data.username,data.name,data.lastname,data.typeOfUser, data.registrationDay, data.birthday,data.street, data.number, data.password,data.isActive,data.x, data.y,data.Id, data.gender);
                        console.log(currentUser);
                        this.errorTextLogin = "";
                        alert("Successful change password");
                        this.authService.logIn(currentUser);
                  }   
                }
                else
                {
                  this.errorTextLogin = "Unsuccsesfull change";
                }      
                  this.regUser = {
                    'username' :  this.authService.currentUser().username,
                    'confirmPassword' : '',
                    'password' : '',
                    'name' : this.authService.currentUserFirstName(),
                    'surname' : this.authService.currentUserSurname(),
                    'birth' : this.authService.currentUserBirth(),
                    'street':this.authService.currentUserAdresa(),
                    'number' : this.authService.currentUserNumber(),
                    'latitude' : this.authService.currentUserLatitude(),
                    'longitude' :this.authService.currentUserLongitude()
                  }
                },
            error =>
            {
                this.errorTextLogin ="Sever internal error";
            }
            
        )
        
      }
    }
  }

 changeReg(){}
}

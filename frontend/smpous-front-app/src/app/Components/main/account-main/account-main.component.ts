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
  selectedFile: File;


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
    /*if(this.regUser.password.length == 0 || this.regUser.confirmPassword.length == 0){
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

        this.authService.changePassword(this.authService.currentUserPassword(),this.regUser.password,this.regUser.confirmPassword,this.authService.currentUserToken()).subscribe(
            (res : any) =>
            {
              this.errorTextLogin = "";
              alert("Successful change password");

              this.http.getUserOnSession(this.regUser.username,this.authService.currentUserToken()).subscribe(
                res => {
                  // console.log(res);
                  let currentUser: CurrentUser;
                  
                  currentUser = new CurrentUser(res.LoggedIn,res.Username,res.Name,res.Surname,this.authService.currentUserRole(),this.authService.currentUserToken(),
                  res.Contact,res.BirthDate,this.authService.currentUserAdresa(), this.regUser.password,res.Approved,
                  res.Latitude, res.Longitude ,res.Path,res.Id);
                  console.log(currentUser);
                  this.authService.logIn(currentUser);
                  //this.header.refreshView();
                  this.regUser = {
                    'username' :  this.authService.currentUserName(),
                    'confirmPassword' : '',
                    'password' : '',
                    'name' : this.authService.currentUserFirstName(),
                    'surname' : this.authService.currentUserSurname(),
                    'birth' : this.authService.currentUserBirth(),
                    'contact' : this.authService.currentUserContact(),
                    'adresa' : this.authService.currentUserAdresa(),
                    'latitude' : this.authService.currentUserLatitude(),
                    'longitude' :this.authService.currentUserLongitude()
                  }
                })
            },
            error =>
            {
                this.errorTextLogin ="Sever internal error";
            }
            
        )
        
      }
    }
    return false;*/
  }

 changeReg(){}
}

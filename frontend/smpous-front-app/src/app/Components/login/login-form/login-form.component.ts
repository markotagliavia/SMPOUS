import { Component, OnInit,Injectable,NgZone, Output,EventEmitter } from '@angular/core';
import { CurrentUser } from '../../../Model/current-user';
import { AuthService } from '../../../Services/auth.service';
import { User } from '../../../Model/user';
import { HttpService } from '../../../Services/http-service.service';
import {
  Router,
  ActivatedRoute
} from '@angular/router'; 

@Injectable()
@Component({
  selector: 'app-login-form',
  templateUrl: './login-form.component.html',
  styleUrls: ['./login-form.component.css'],
})
export class LoginFormComponent implements OnInit {

	
  ngZone: NgZone;
  user: User;
  errorText : string;
  response: any;
  
  constructor(public httpService: HttpService, private router: Router, private authService: AuthService) { 
	this.ngZone = new NgZone({enableLongStackTrace: false});
	this.user = {
		'username' : '',
		'password' : ''
	}
	this.errorText = "";
  }
  
  

  ngOnInit() {
  }
  
  loginUser(): boolean {
	if(this.user.username.length == 0 || this.user.password.length == 0){
		this.errorText = "All fields are required";
		return false;
	}
	else
	{
		this.errorText = "";
	}
	
    console.log('Dobili smo: ', JSON.stringify(this.user));
	this.httpService.logIn(this.user).subscribe(
      (res: any) => {
        console.log(res);
        if(res._body != "")
        {
						this.response = res;
            let data = res.json();
            console.log(data);
            if(data)  
            {
                  // console.log(res);
                  let currentUser: CurrentUser;
                  
                  currentUser = new CurrentUser(true,data.username,data.name,data.lastname,data.typeOfUser, data.registrationDay, data.birthday,data.street, data.number, this.user.password,data.isActive,data.x, data.y,data.Id, data.gender);
                  console.log(currentUser);
                  this.authService.logIn(currentUser);
                  location.reload();
            }   
          }
          else
          {
            this.errorText = "Your credentials are not valid or your account is not activated";
          }      

                    },
      error => {
                  console.log(error);
                  this.errorText = "Your data are not valid";
               }

    );
    return false; 
  }

}

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
						this.response = res;
						let data = res.json();
            if(data)  
            {
                  // console.log(res);
                  let currentUser: CurrentUser;
                  
                  currentUser = new CurrentUser(true,res.username,res.name,res.lastname,res.typeOfUser, res.registrationDay, res.birthday,res.street, res.number, this.user.password,res.isActive,res.x, res.y,res.Id, res.gender);
                  console.log(currentUser);
                  this.authService.logIn(currentUser);
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

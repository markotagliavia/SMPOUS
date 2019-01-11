import { Component, OnInit, Injectable, Input } from '@angular/core';
import { AuthService } from '../../../../Services/auth.service';
import { HttpService } from '../../../../Services/http-service.service';
import { AppUser } from '../../../../Model/app-user';
import { CurrentUser } from '../../../../Model/current-user';
import {SafeUrl,DomSanitizer} from '@angular/platform-browser/';
@Injectable()
@Component({
  selector: 'app-table-row-user',
  templateUrl: './table-row-user.component.html',
  styleUrls: ['./table-row-user.component.css']
})
export class TableRowUserComponent implements OnInit {

  @Input() user: AppUser
  client : boolean;
  unverified : boolean;
  currUserPom : CurrentUser;

  constructor(private http: HttpService, private authService: AuthService,private _DomSanitizationService: DomSanitizer) {
    this.client = false;
    this.unverified = false;
   }

  ngOnInit() {
    this.refresh();
  }

  refresh()
  {
    if(this.user.Role == "AppUser" && this.user.Approved)
    {
      this.client = true;
      this.unverified = false;
    }
    else if(this.user.Role == "AppUser" && !this.user.Approved)
    {
      this.unverified = true;
      this.client = false;
    }
    else if(this.user.Role == "Manager" && this.user.Approved)
    {
      this.client = false;
      this.unverified = false;
    }
    else if(this.user.Role == "Manager" && !this.user.Approved)
    {
      this.unverified = true;
      this.client = false;
    }
  }

  verifyUser()
  {
    /*this.user.Approved = true;
    this.http.approveUser(this.user, this.authService.currentUserToken(),true).subscribe(
      (res : any) => { 
          this.refresh();
      },
      error =>{
          console.log(error);
          window.alert(error);
      });*/
  }

}

import { Component, OnInit, Injectable, Input } from '@angular/core';
import { AuthService } from '../../../../Services/auth.service';
import { HttpService } from '../../../../Services/http-service.service';
import { CurrentUser } from '../../../../Model/current-user';
import {SafeUrl,DomSanitizer} from '@angular/platform-browser/';
@Injectable()
@Component({
  selector: 'app-table-row-user',
  templateUrl: './table-row-user.component.html',
  styleUrls: ['./table-row-user.component.css']
})
export class TableRowUserComponent implements OnInit {

  @Input() user: CurrentUser
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
    if(this.user.role == "registered")
    {
      this.client = true;
      this.unverified = false;
    }
    else if(this.user.role == "unregistered")
    {
      this.unverified = true;
      this.client = false;
    }
  }

  verifyUser()
  {
    this.user.approved = true;
    this.user.role = "registered";
    this.http.approveUser(this.authService.currentUserUsername(), this.user.username).subscribe(
      (res : any) => { 
          this.refresh();
      },
      error =>{
          console.log(error);
          window.alert(error);
      });
  }

  UNverifyUser()
  {
    this.user.approved = false;
    this.user.role = "unregistered";
    this.http.UNapproveUser(this.authService.currentUserUsername(), this.user.username).subscribe(
      (res : any) => { 
          this.refresh();
      },
      error =>{
          console.log(error);
          window.alert(error);
      });
  }

}

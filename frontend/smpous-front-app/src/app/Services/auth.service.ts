import { CurrentUser } from "../Model/current-user";
import { Http, Response, RequestOptions, Headers } from '@angular/http';
import { Injectable } from "@angular/core";
import { AppUser } from '../Model/app-user';
import { Observable } from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class AuthService {

    loggedIn : boolean;

    constructor(private http: Http){
        
    }

    logIn(currentUser: CurrentUser): void{
        localStorage.setItem("currentUser", JSON.stringify(currentUser));
    }

    logOut(): void{
        localStorage.removeItem("currentUser");
    }

    isLoggedIn(): boolean{
        if(localStorage.getItem("currentUser") !== null)
            return true;
        else
            return false;
    }

    isLoggedOut(): boolean{
        if(localStorage.getItem("currentUser") !== null)
            return false;
        else
            return true;
    }

    isLoggedInRole(role: string): boolean {
        if(role == 'Manager')
        {
            return false;
        }
        else if(role == 'Admin')
        {
            role = 'admin';
        }
        else if(role == 'AppUser')
        {
            role = 'registered';
        }
        let curretUser = localStorage.getItem("currentUser");
        if ( curretUser !== null){
            let user: CurrentUser = JSON.parse(curretUser);
            if (user.role == role){
                return true;
            }
            else {
                return false;
            }
        }
        else {
            return false;
        }
    }

    currentUserId(): string {
        let curretUser = localStorage.getItem("currentUser");
        if ( curretUser !== null){
            let user: CurrentUser = JSON.parse(curretUser);
            return user.id;
        }
        else
        {
            return "-1";
        }
    }

    currentUserUsername(): string {
        let curretUser = localStorage.getItem("currentUser");
        if ( curretUser !== null){
            let user: CurrentUser = JSON.parse(curretUser);
            return user.username;
        }
        else
        {
            return "";
        }
    }

    currentUser(): CurrentUser{
        let curretUser = localStorage.getItem("currentUser");
        if ( curretUser !== null){
            let user: CurrentUser = JSON.parse(curretUser);
            return user;
        }
        else
        {
            return null;
        }
    }

    currentUserApproved(): boolean {
        let curretUser = localStorage.getItem("currentUser");
        if ( curretUser !== null){
            let user: CurrentUser = JSON.parse(curretUser);
            return user.approved;
        }
        else
        {
            return false;
        }
    }

    currentUserLatitude(): number {
        let curretUser = localStorage.getItem("currentUser");
        if ( curretUser !== null){
            let user: CurrentUser = JSON.parse(curretUser);
            return user.latitude;
        }
        else
        {
            return 0;
        }
    }

    currentUserLongitude(): number {
        let curretUser = localStorage.getItem("currentUser");
        if ( curretUser !== null){
            let user: CurrentUser = JSON.parse(curretUser);
            return user.longitude;
        }
        else
        {
            return 0;
        }
    }

    currentUserSurname(): string {
        let currentUser = localStorage.getItem("currentUser");
        if (currentUser !== null) {
            let user: CurrentUser = JSON.parse(currentUser);
            return user.surname;
        }
        else {
            return null;
        }
    }

    currentUserBirth(): string {
        let currentUser = localStorage.getItem("currentUser");
        if (currentUser !== null) {
            let user: CurrentUser = JSON.parse(currentUser);
            return user.birth;
        }
        else {
            return null;
        }
    }

    currentUserAdresa(): string {
        let currentUser = localStorage.getItem("currentUser");
        if (currentUser !== null) {
            let user: CurrentUser = JSON.parse(currentUser);
            return user.street;
        }
        else {
            return null;
        }
    }

    currentUserNumber(): number {
        let currentUser = localStorage.getItem("currentUser");
        if (currentUser !== null) {
            let user: CurrentUser = JSON.parse(currentUser);
            return user.number;
        }
        else {
            return -1;
        }
    }

    currentUserRegistrationDay(): string {
        let currentUser = localStorage.getItem("currentUser");
        if (currentUser !== null) {
            let user: CurrentUser = JSON.parse(currentUser);
            return user.registrationDay;
        }
        else {
            return null;
        }
    }

    currentUserFirstName(): string {
        let currentUser = localStorage.getItem("currentUser");
        if (currentUser !== null) {
            let user: CurrentUser = JSON.parse(currentUser);
            return user.name;
        }
        else {
            return null;
        }
    }


    currentUserName(): string {
        let currentUser = localStorage.getItem("currentUser");
        if (currentUser !== null) {
            let user: CurrentUser = JSON.parse(currentUser);
            return user.username;
        }
        else {
            return null;
        }
    }

    currentUserPassword(): string{
        let currentUser = localStorage.getItem("currentUser");
        if (currentUser !== null) {
            let user: CurrentUser = JSON.parse(currentUser);
            return user.password;
        }
        else {
            return null;
        }
    }

    currentUserRole(): string { 
        let currentUser = localStorage.getItem("currentUser");
        if (currentUser !== null) {
            let user: CurrentUser = JSON.parse(currentUser);
            return user.role;
        }
        else {
            return null;
        }
    }

    

    
    changePassword(userOnSession : string, currentPass: string, newPass: string, confirmPass: string): Observable<any>{
       
        const headers: Headers = new Headers();
        headers.append('Accept', 'application/json');
        headers.append('Content-type', 'application/json');

        const opts: RequestOptions = new RequestOptions();
        opts.headers = headers;

        return this.http.post(`http://localhost:8765/user-service/users/changePassword`,
         JSON.stringify({
             userOnSession : userOnSession,
             oldPassword: currentPass,
             newPassword: newPass,
             confirmPassword: confirmPass
         }), opts);
    }

    changeInfo(userOnSession : string, name: string, surname: string, street: string, num : number, x : number, y : number, birth : string): Observable<any>{
       
        const headers: Headers = new Headers();
        headers.append('Accept', 'application/json');
        headers.append('Content-type', 'application/json');

        const opts: RequestOptions = new RequestOptions();
        opts.headers = headers;

        return this.http.post(`http://localhost:8765/user-service/users/changeInfo`,
         JSON.stringify({
             userOnSession : userOnSession,
             name: name,
             lastname: surname,
             street: street,
             number : num,
             x : x,
             y : y,
             birth : birth
         }), opts);
    }
}

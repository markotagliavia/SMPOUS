import { Injectable } from '@angular/core';
import { Http, Response, RequestOptions, Headers } from '@angular/http';
import { User } from '../Model/user';
import { IdentityUser } from '../Model/identity-user';
import { AppUser } from '../Model/app-user';
import { TypeOfVehicle} from '../Model/type-of-vehicle';
import { Observable } from "rxjs";
import { map } from 'rxjs/operators';
import { CurrentUser } from '../Model/current-user';
@Injectable({
  providedIn: 'root'
})
export class HttpService {

  constructor(private http: Http) { }
  
  private extractData(res: Response) {
        let body = res.json();
        return body || [];
    }
	
  logIn(user: User): Observable<any> {
	  
	    console.log(`Stiglo: ${user.username} i : ${user.password}`);
        const headers: Headers = new Headers();
        headers.append('Accept', 'application/json');
        headers.append('Content-type', 'application/json');

        const opts: RequestOptions = new RequestOptions();
        opts.headers = headers;

        return this.http.post(
            'http://localhost:8765/user-service/users/login',
            JSON.stringify({
                username: user.username,
                password: user.password,
            }), opts);
    }

    logOut(user: string): Observable<any> {
	  
	    console.log(`Stiglo: ${user}`);
        const headers: Headers = new Headers();
        headers.append('Accept', 'application/json');
        headers.append('Content-type', 'application/json');

        const opts: RequestOptions = new RequestOptions();
        opts.headers = headers;

        return this.http.post(
            'http://localhost:8765/user-service/users/logout',
            JSON.stringify({
                username: user
            }), opts);
    }
	
	register(user: IdentityUser) {
	  
	    //console.log(`Stiglo: ${user.username} i : ${user.password} i ${user.name} i ${user.surname} i ${user.birth} i ${user.contact} i ${user.adresa}`);
        
        const headers: Headers = new Headers();
        headers.append('Accept', 'application/json');
        headers.append('Content-type', 'application/json');

        const opts: RequestOptions = new RequestOptions();
        opts.headers = headers;

        return this.http.post(
            'http://localhost:8765/user-service/users/register',
            JSON.stringify({
                id: '',
                name: user.name,
                lastname: user.surname,
                username: user.username,
                password: user.password,
                registrationDay: '1971-01-01',
                birthday: user.birth,
                gender: true,
                isActive: false,
                x : user.latitude,
                y : user.longitude,
                street: user.street,
                number: user.number,
                typeOfUser: 'unregistered'
            }), opts);
        
    }

    approveUser(userOnSession : string, user: string)
    {
        const headers: Headers = new Headers();
        headers.append('Accept', 'application/json');
        headers.append('Content-type', 'application/json');


        const opts: RequestOptions = new RequestOptions();
        opts.headers = headers;

        return this.http.post(
            `http://localhost:8765/user-service/users/activate`
            ,
            JSON.stringify({
            userOnSession: userOnSession,
            userToActivate: user,
            }), opts);
        
    }

    UNapproveUser(userOnSession : string, user: string)
    {
        const headers: Headers = new Headers();
        headers.append('Accept', 'application/json');
        headers.append('Content-type', 'application/json');


        const opts: RequestOptions = new RequestOptions();
        opts.headers = headers;

        return this.http.post(
            `http://localhost:8765/user-service/users/deactivate`
            ,
            JSON.stringify({
            userOnSession: userOnSession,
             userToDeActivate: user,
            }), opts);
        
    }


    putUser(user: CurrentUser, token: string) : Observable<any>
    {
        /*const headers: Headers = new Headers();
        headers.append('Content-type', 'application/json');
        let usertoken = `Bearer ${token}`;
        headers.append('Authorization', usertoken);

        const opts: RequestOptions = new RequestOptions();
        opts.headers = headers;

        return this.http.put(
            `http://localhost:51432/api/AppUser/PutAppUser/${user.id}`
            ,
            JSON.stringify({
             Id: user.id,
             Name: user.name,
             Surname: user.surname,
             Contact: user.contact,
             Username: user.username,
             BirthDate: user.birth,
             Approved: user.approved,
             LoggedIn: user.login,
             Latitude : user.latitude,
             Longitude : user.longitude,
             Path: user.Path
            }), opts);*/
            return null;
    }

    getUserOnSession(username: string, token: string): Observable<any> {
        const headers: Headers = new Headers();
        headers.append('Accept', 'application/json');
        headers.append('Content-type', 'application/json');

        const opts: RequestOptions = new RequestOptions();
        opts.headers = headers;
        return this.http.post(
            'http://localhost:8765/user-service/users/logout',
            JSON.stringify({
                userOnSession: username
            }), opts);
    }

    createTypeOfVehicle(type : TypeOfVehicle, token: string): Observable<any>
    {
        const headers: Headers = new Headers();
        headers.append('Content-type', 'application/json');
        let usertoken = `Bearer ${token}`;
        headers.append('Authorization', usertoken);

        const opts: RequestOptions = new RequestOptions();
        opts.headers = headers;
        return this.http.post(
            'http://localhost:51432/api/TypeOfVehicle/PostTypeOfVehicle',
            JSON.stringify({
                Id: type.Id,
                Name: type.Name
            }), opts);
    }

    getTypeOfVehicle(token: string): Observable<any>
    {
        const headers: Headers = new Headers();
        headers.append('Content-type', 'application/json');
        let usertoken = `Bearer ${token}`;
        headers.append('Authorization', usertoken);

        const opts: RequestOptions = new RequestOptions();
        opts.headers = headers;
        var url = 'http://localhost:51432/api/TypeOfVehicle/GetAllTypeOfVehicles';
        return this.http.get(url, opts).pipe(map((res: Response) => this.extractData(res)));
    }

    putTypeOfVehicle(type: TypeOfVehicle, novi: string, token: string) : Observable<any>
    {
        const headers: Headers = new Headers();
        headers.append('Content-type', 'application/json');
        let usertoken = `Bearer ${token}`;
        headers.append('Authorization', usertoken);

        const opts: RequestOptions = new RequestOptions();
        opts.headers = headers;

        return this.http.put(
            `http://localhost:51432/api/TypeOfVehicle/PutTypeOfVehicle/${type.Id}`
            ,
            JSON.stringify({
                Id: type.Id,
                Name: novi,
            }), opts);
    }

    deleteTypeOfVehicle(type: TypeOfVehicle, token: string) : Observable<any>
    {
        const headers: Headers = new Headers();
        headers.append('Content-type', 'application/json');
        let usertoken = `Bearer ${token}`;
        headers.append('Authorization', usertoken);

        const opts: RequestOptions = new RequestOptions();
        opts.headers = headers;

        return this.http.delete(
            `http://localhost:51432/api/TypeOfVehicle/DeleteTypeOfVehicle/${type.Id}`
            , opts);
    }


    uploadPicture(userid: number, file : File, token: string):Observable<any>
    {

        const headers: Headers = new Headers();
        //headers.append('Content-type', 'multipart/form-data');
        let usertoken = `Bearer ${token}`;
        headers.append('Authorization', usertoken);

        const opts: RequestOptions = new RequestOptions();
        opts.headers = headers;
        let formData:FormData = new FormData();
        formData.append('uploadFile', file, file.name);
        
        return this.http.post(
            `http://localhost:51432/api/Upload/PostUserImage/${userid}`,
            formData, opts);
       
    }

    getAllUsers(token: string):Observable<any>
    {

        const headers: Headers = new Headers();
        headers.append('Content-type', 'application/json');
        let usertoken = `Bearer ${token}`;
        headers.append('Authorization', usertoken);

        const opts: RequestOptions = new RequestOptions();
        opts.headers = headers;

        return this.http.get(
            `http://localhost:51432/api/AppUser/GetAllUsers`
            , opts).pipe(map((res: Response) => this.extractData(res)));

    }

    getAllAppUsers(userOnSession: string):Observable<any>
    {

        const headers: Headers = new Headers();
        headers.append('Accept', 'application/json');
        headers.append('Content-type', 'application/json');


        const opts: RequestOptions = new RequestOptions();
        opts.headers = headers;

        
        return this.http.get(
            `http://localhost:8765/user-service/users/all`
            , opts).pipe(map((res: Response) => this.extractData(res)));

    }

    getAllAppUsersWithFilter(userOnSession: string, username : string, firstName : string, lastname : string, isActive : boolean, radius : number, x : number, y : number):Observable<any>
    {

        const headers: Headers = new Headers();

            headers.append('Accept', 'application/json');
            headers.append('Content-type', 'application/json');
    
            const opts: RequestOptions = new RequestOptions();
            opts.headers = headers;
            return this.http.post(
                'http://localhost:8765/user-service/users/search/findGeneral',
                JSON.stringify({
                    userOnSession: userOnSession,
                    username : username, 
                    firstName : firstName,
                    surname : lastname,
                    isActive : isActive,
                    radius : radius,
                    x:x,
                    y:y
                }), opts).pipe(map((res: Response) => this.extractData(res)));
    }

    getAllManagers(token: string):Observable<any>
    {

        const headers: Headers = new Headers();
        headers.append('Content-type', 'application/json');
        let usertoken = `Bearer ${token}`;
        headers.append('Authorization', usertoken);

        const opts: RequestOptions = new RequestOptions();
        opts.headers = headers;

        return this.http.get(
            `http://localhost:51432/api/AppUser/GetAllManagers`
            , opts).pipe(map((res: Response) => this.extractData(res)));

    }
}

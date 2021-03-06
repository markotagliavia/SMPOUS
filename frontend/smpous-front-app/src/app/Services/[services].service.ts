import { Injectable } from '@angular/core';
import { Http, Response, RequestOptions, Headers } from '@angular/http';
import { User } from '../Model/user';
import { IdentityUser } from '../Model/identity-user';
import { Service } from '../Model/service'
import { Branch } from '../Model/branch'
import { Vehicle } from '../Model/vehicle'
import { AppUser } from '../Model/app-user';
import { Rate } from '../Model/rate';
import { Observable } from "rxjs";
import { map } from 'rxjs/operators';
import { PriceList } from '../Model/pricelist';
import { Reservation } from '../Model/reservation';
import { Cinema } from '../Model/cinema';
import { Theater } from '../Model/theather';


@Injectable({
  providedIn: 'root'
})
export class ServiceManager {

  constructor(private http: Http) { }
  
  private extractData(res: Response) {
        let body = res.json();
        return body || [];
    }

  //branch section ----------------------------------------------------------------------------
  createBranch(branch : Branch, token: string): Observable<any>
  {
    const headers: Headers = new Headers();
    headers.append('Content-type', 'application/json');
    let usertoken = `Bearer ${token}`;
    headers.append('Authorization', usertoken);

    const opts: RequestOptions = new RequestOptions();
    opts.headers = headers;
    return this.http.post(
        'http://localhost:51432/api/Branches/PostBranch',
        JSON.stringify({
            Id: branch.Id,
            Name: branch.Name,
            Latitude: branch.Latitude,
            Longitude: branch.Longitude,
            Address: branch.Address,
            ServiceId: branch.ServiceId
        }), opts);
  }

  getBranchPicture(id : number, token : string)
  {
    const headers: Headers = new Headers();
    headers.append('Content-type', 'application/json');
    let usertoken = `Bearer ${token}`;
    headers.append('Authorization', usertoken);

    const opts: RequestOptions = new RequestOptions();
    opts.headers = headers;

    var url = `http://localhost:51432/api/Branches/Path/${id}`;
    return this.http.get(url, opts).pipe(map((res: Response) => this.extractData(res)));
  }

  putBranch(branch: Branch, branchNova : Branch, token: string) : Observable<any>
  {
    const headers: Headers = new Headers();
    headers.append('Content-type', 'application/json');
    let usertoken = `Bearer ${token}`;
    headers.append('Authorization', usertoken);

    const opts: RequestOptions = new RequestOptions();
    opts.headers = headers;

    return this.http.put(
        `http://localhost:51432/api/Branches/PutBranch/${branch.Id}`
        ,
        JSON.stringify({
          Id: branch.Id,
          Name: branchNova.Name,
          Latitude: branchNova.Latitude,
          Longitude: branchNova.Longitude,
          Address: branchNova.Address,
          ServiceId: branch.ServiceId
        }), opts);
  }

  deleteBranch(branch: Branch, token: string) : Observable<any>
  {
    const headers: Headers = new Headers();
    headers.append('Content-type', 'application/json');
    let usertoken = `Bearer ${token}`;
    headers.append('Authorization', usertoken);

    const opts: RequestOptions = new RequestOptions();
    opts.headers = headers;

    return this.http.delete(
        `http://localhost:51432/api/Branches/DeleteBranch/${branch.Id}`
        , opts);
  }

  getBranches(token: string): Observable<any>
  {
    const headers: Headers = new Headers();
    headers.append('Content-type', 'application/json');
    let usertoken = `Bearer ${token}`;
    headers.append('Authorization', usertoken);

    const opts: RequestOptions = new RequestOptions();
    opts.headers = headers;
    var url = 'http://localhost:51432/api/Branches/GetAllBranches';
    return this.http.get(url, opts).pipe(map((res: Response) => this.extractData(res)));
  }

  uploadBranchPicture(branchid: number, file : File, token: string):Observable<any>
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
            `http://localhost:51432/api/Upload/PostBranchImage/${branchid}`,
            formData, opts);
       
    }
    //end of branch section ----------------------------------------------------------------------------

    //service section ----------------------------------------------------------------------------
  addNewService(service : Service,id : number,  token : string):Observable<any>
  {
    const headers: Headers = new Headers();
    headers.append('Content-type', 'application/json');
    let usertoken = `Bearer ${token}`;
    headers.append('Authorization', usertoken);

    const opts: RequestOptions = new RequestOptions();
    opts.headers = headers;
    return this.http.post(
        'http://localhost:51432/api/Services/PostService',
        JSON.stringify({
          Id: service.Id,
          Name: service.Name, 
          Email: service.Email,
          Description: service.Description,
          Contact: service.Contact,
          AppUserId: id,
          Path: service.Path,
          Approved: false,
          AverageMark: 0
        }), opts);
  }

  putService(serviceNovi : Service, token: string) : Observable<any>
  {
    const headers: Headers = new Headers();
    headers.append('Content-type', 'application/json');
    let usertoken = `Bearer ${token}`;
    headers.append('Authorization', usertoken);

    const opts: RequestOptions = new RequestOptions();
    opts.headers = headers;

    return this.http.put(
        `http://localhost:51432/api/Services/PutService/${serviceNovi.Id}`
        ,
        JSON.stringify({
          Id: serviceNovi.Id,
          Name: serviceNovi.Name, 
          Email: serviceNovi.Email,
          Description: serviceNovi.Description,
          Contact: serviceNovi.Contact,
          AppUserId: serviceNovi.AppUserId,
          Path: serviceNovi.Path,
          Approved: serviceNovi.Approved,
          AverageMark: serviceNovi.AverageMark
        }), opts);
  }

  approveService(service : Service, token: string) : Observable<any>
  {
    const headers: Headers = new Headers();
    headers.append('Content-type', 'application/json');
    let usertoken = `Bearer ${token}`;
    headers.append('Authorization', usertoken);

    const opts: RequestOptions = new RequestOptions();
    opts.headers = headers;

    return this.http.put(
        `http://localhost:51432/api/Services/ApproveService/${service.Id}`,
        JSON.stringify({
          Id: service.Id,
          Name: service.Name, 
          Email: service.Email,
          Description: service.Description,
          Contact: service.Contact,
          AppUserId: service.AppUserId,
          Path: service.Path,
          Approved: true,
          AverageMark: service.AverageMark
        })
       , opts);
  }

  getServices(token: string): Observable<any>
  {
    const headers: Headers = new Headers();
    headers.append('Content-type', 'application/json');
    let usertoken = `Bearer ${token}`;
    headers.append('Authorization', usertoken);

    const opts: RequestOptions = new RequestOptions();
    opts.headers = headers;
    var url = 'http://localhost:51432/api/Services/GetAllServices';
    return this.http.get(url, opts).pipe(map((res: Response) => this.extractData(res)));
  }




  getService(token: string, serviceId : number) : any
  {
    const headers: Headers = new Headers();
    headers.append('Content-type', 'application/json');
    let usertoken = `Bearer ${token}`;
    headers.append('Authorization', usertoken);

    const opts: RequestOptions = new RequestOptions();
    opts.headers = headers;
    var url = `http://localhost:51432/api/Services/GetService/${serviceId}`;
    return this.http.get(url, opts).pipe(map((res: Response) => this.extractData(res)));
  }

  deleteService(service: Service, token: string) : Observable<any>
  {
    const headers: Headers = new Headers();
    headers.append('Content-type', 'application/json');
    let usertoken = `Bearer ${token}`;
    headers.append('Authorization', usertoken);

    const opts: RequestOptions = new RequestOptions();
    opts.headers = headers;

    return this.http.delete(
        `http://localhost:51432/api/Services/DeleteService/${service.Id}`
        , opts);
  }

  uploadServicePicture(serviceid: number, file : File, token: string):Observable<any>
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
            `http://localhost:51432/api/Upload/PostServiceImage/${serviceid}`,
            formData, opts);
       
    }


    //end of service section ----------------------------------------------------------------------------


  //cars section ----------------------------------------------------------------------------
  addNewCar(car : Vehicle,id : number,  token : string):Observable<any>
  {
    const headers: Headers = new Headers();
    headers.append('Content-type', 'application/json');
    let usertoken = `Bearer ${token}`;
    headers.append('Authorization', usertoken);

    const opts: RequestOptions = new RequestOptions();
    opts.headers = headers;
    return this.http.post(
        'http://localhost:51432/api/Vehicles/PostVehicle',
        JSON.stringify({
          Id: car.Id,
          Mark: car.Mark,
          Avaliable : car.Available,
          Model: car.Model,
          Description: car.Description,
          Year : car.Year,
          TypeOfVehicleId: car.TypeOfVehicleId,
          ServiceId: car.ServiceId
        }), opts);
  }

  addNewPrice(price : PriceList, token : string) : Observable<any>
  {
    const headers: Headers = new Headers();
    headers.append('Content-type', 'application/json');
    let usertoken = `Bearer ${token}`;
    headers.append('Authorization', usertoken);

    const opts: RequestOptions = new RequestOptions();
    opts.headers = headers;
    return this.http.post(
      'http://localhost:51432/api/PriceLists/PostPriceList',
      JSON.stringify({
        Id: price.Id,
        VehicleId: price.VehicleId,
        StartDate : price.StartDate,
        EndDate : price.EndDate,
        Price : price.Price
      }), opts); 
  }

  putCar(car: Vehicle, token: string) : Observable<any>
  {
    const headers: Headers = new Headers();
    headers.append('Content-type', 'application/json');
    let usertoken = `Bearer ${token}`;
    headers.append('Authorization', usertoken);

    const opts: RequestOptions = new RequestOptions();
    opts.headers = headers;

    return this.http.put(
        `http://localhost:51432/api/Vehicles/PutVehicle/${car.Id}`
        ,
        JSON.stringify({
          Id: car.Id,
          Mark: car.Mark,
          Avaliable : car.Available,
          Model: car.Model,
          Description: car.Description,
          Year : car.Year,
          TypeOfVehicleId: car.TypeOfVehicleId,
          ServiceId: car.ServiceId
        }), opts);
  }

  getCars(token: string): Observable<any>
  {
    const headers: Headers = new Headers();
    headers.append('Content-type', 'application/json');
    let usertoken = `Bearer ${token}`;
    headers.append('Authorization', usertoken);

    const opts: RequestOptions = new RequestOptions();
    opts.headers = headers;
    var url = 'http://localhost:51432/api/Vehicles/GetAllVehicles';
    return this.http.get(url, opts).pipe(map((res: Response) => this.extractData(res)));
  }

  getCarsPaginigWithFilter(token: string, pageNumber : number, pageSize : number
    ,manuName : string,modelName : string, year : string, fromPrice : number, toPrice : number, type : string, serviceId : number): Observable<any>
    {
      const headers: Headers = new Headers();
      headers.append('Content-type', 'application/json');
      let usertoken = `Bearer ${token}`;
      headers.append('Authorization', usertoken);
  
      const opts: RequestOptions = new RequestOptions();
      opts.headers = headers;
      var url = `http://localhost:51432/api/Vehicles/PaginationWithFilter?pageNumber=${pageNumber}&pageSize=${pageSize}&manuName=${manuName}&modelName=${modelName}&year=${year}&fromPrice=${fromPrice}&toPrice=${toPrice}&type=${type}&serviceId=${serviceId}`;
      return this.http.get(url, opts).pipe(map((res: Response) => this.extractData(res)));
    }
  
    getPaginationWithFilterCount(token: string, pageNumber : number, pageSize : number
      ,manuName : string,modelName : string, year : string, fromPrice : number, toPrice : number, type : string, serviceId : number)
    {
      const headers: Headers = new Headers();
      headers.append('Content-type', 'application/json');
      let usertoken = `Bearer ${token}`;
      headers.append('Authorization', usertoken);
  
      const opts: RequestOptions = new RequestOptions();
      opts.headers = headers;
      var url = `http://localhost:51432/api/Vehicles/PaginationWithFilterCount?pageNumber=${pageNumber}&pageSize=${pageSize}&manuName=${manuName}&modelName=${modelName}&year=${year}&fromPrice=${fromPrice}&toPrice=${toPrice}&type=${type}&serviceId=${serviceId}`;
      return this.http.get(url, opts).pipe(map((res: Response) => this.extractData(res)));
    }

  getPrice(token: string, carId : number) : Observable<any>
  {
    const headers: Headers = new Headers();
    headers.append('Content-type', 'application/json');
    let usertoken = `Bearer ${token}`;
    headers.append('Authorization', usertoken);

    const opts: RequestOptions = new RequestOptions();
    opts.headers = headers;
    var url = `http://localhost:51432/api/Vehicles/GetPrice/${carId}`;
    return this.http.get(url, opts).pipe(map((res: Response) => this.extractData(res)));
  }

  

  getCar(token: string, carId : number) : any
  {
    const headers: Headers = new Headers();
    headers.append('Content-type', 'application/json');
    let usertoken = `Bearer ${token}`;
    headers.append('Authorization', usertoken);

    const opts: RequestOptions = new RequestOptions();
    opts.headers = headers;
    var url = `http://localhost:51432/api/Vehicles/GetVehicle/${carId}`;
    return this.http.get(url, opts).pipe(map((res: Response) => this.extractData(res)));
  }

  deleteCar(car : Vehicle, token: string) : Observable<any>
  {
    const headers: Headers = new Headers();
    headers.append('Content-type', 'application/json');
    let usertoken = `Bearer ${token}`;
    headers.append('Authorization', usertoken);

    const opts: RequestOptions = new RequestOptions();
    opts.headers = headers;

    return this.http.delete(
        `http://localhost:51432/api/Vehicles/DeleteVehicle/${car.Id}`
        , opts);
  }

  setAvailable(carId: number,token : string):Observable<any>
  {
    const headers: Headers = new Headers();
    headers.append('Content-type', 'application/json');
    let usertoken = `Bearer ${token}`;
    headers.append('Authorization', usertoken);

    const opts: RequestOptions = new RequestOptions();
    opts.headers = headers;

    return this.http.get(
        `http://localhost:51432/api/Vehicles/SetAvaiable/${carId}`
        , opts);
  }

  uploadCarPicture(carid: number, files : File[], token: string):Observable<any>
  {

      const headers: Headers = new Headers();
      //headers.append('Content-type', 'multipart/form-data');
      let usertoken = `Bearer ${token}`;
      headers.append('Authorization', usertoken);

      const opts: RequestOptions = new RequestOptions();
      opts.headers = headers;
      let formData:FormData = new FormData();
      for(let i = 0; i < files.length; i++)
      {
        formData.append('uploadFile'+i, files[i], files[i].name);
      }
      
      
      
      return this.http.post(
          `http://localhost:51432/api/Upload/PostCarImage/${carid}`,
          formData, opts);
      
  }
    //end of cars section ----------------------------------------------------------------------------
    //reservation section ----------------------------------------------------------------------------
  checkReservation(reservation : Reservation, token: string)
  {
    const headers: Headers = new Headers();
    headers.append('Content-type', 'application/json');
    let usertoken = `Bearer ${token}`;
    headers.append('Authorization', usertoken);

    const opts: RequestOptions = new RequestOptions();
    opts.headers = headers;

    return this.http.post(
      `http://localhost:51432/api/Reservations/CheckReservation`
      ,
      JSON.stringify({
        Id: reservation.Id,
        VehicleId : reservation.VehicleId,
        StartDate : reservation.StartDate,
        EndDate : reservation.EndDate,
        AppUserId : reservation.AppUserId,
        TotalPrice : reservation.TotalPrice,
        Expired : reservation.Expired
      }), opts);
  }

  checkReservationEdit(reservation : Reservation, token: string)
  {
    const headers: Headers = new Headers();
    headers.append('Content-type', 'application/json');
    let usertoken = `Bearer ${token}`;
    headers.append('Authorization', usertoken);

    const opts: RequestOptions = new RequestOptions();
    opts.headers = headers;

    return this.http.post(
      `http://localhost:51432/api/Reservations/CheckReservationEdit`
      ,
      JSON.stringify({
        Id: reservation.Id,
        VehicleId : reservation.VehicleId,
        StartDate : reservation.StartDate,
        EndDate : reservation.EndDate,
        AppUserId : reservation.AppUserId,
        TotalPrice : reservation.TotalPrice,
        Expired : reservation.Expired
      }), opts);
  }

  addReservation(reservation : Reservation, token: string)
  {
    const headers: Headers = new Headers();
    headers.append('Content-type', 'application/json');
    let usertoken = `Bearer ${token}`;
    headers.append('Authorization', usertoken);

    const opts: RequestOptions = new RequestOptions();
    opts.headers = headers;

    return this.http.post(
      `http://localhost:51432/api/Reservations/PostReservation`
      ,
      JSON.stringify({
        Id: reservation.Id,
        VehicleId : reservation.VehicleId,
        StartDate : reservation.StartDate,
        EndDate : reservation.EndDate,
        AppUserId : reservation.AppUserId,
        TotalPrice : reservation.TotalPrice,
        Expired : reservation.Expired,
        BranchReservations : reservation.BranchReservations,
      }), opts);
  }


  editReservation(reservation : Reservation, token: string)
  {
    const headers: Headers = new Headers();
    headers.append('Content-type', 'application/json');
    let usertoken = `Bearer ${token}`;
    headers.append('Authorization', usertoken);

    const opts: RequestOptions = new RequestOptions();
    opts.headers = headers;

    return this.http.put(
      `http://localhost:51432/api/Reservations/PutReservation/${reservation.Id}`
      ,
      JSON.stringify({
        Id: reservation.Id,
        VehicleId : reservation.VehicleId,
        StartDate : reservation.StartDate,
        EndDate : reservation.EndDate,
        AppUserId : reservation.AppUserId,
        TotalPrice : reservation.TotalPrice,
        Expired : reservation.Expired,
        BranchReservations : reservation.BranchReservations,
      }), opts);
  }

  getReservationsUser(id : number, token : string)
  {
    const headers: Headers = new Headers();
    headers.append('Content-type', 'application/json');
    let usertoken = `Bearer ${token}`;
    headers.append('Authorization', usertoken);

    const opts: RequestOptions = new RequestOptions();
    opts.headers = headers;

    return this.http.get(
      `http://localhost:51432/api/Reservations/GetUserReservation/${id}`
      ,opts
    ).pipe(map((res: Response) => this.extractData(res)));
  }

  getReservation(id : number, token : string)
  {
    const headers: Headers = new Headers();
    headers.append('Content-type', 'application/json');
    let usertoken = `Bearer ${token}`;
    headers.append('Authorization', usertoken);

    const opts: RequestOptions = new RequestOptions();
    opts.headers = headers;
    return this.http.get(
      `http://localhost:51432/api/Reservations/GetReservation/${id}`
      ,opts
    ).pipe(map((res: Response) => this.extractData(res)));
  }

  deleteReservation(id: number, token:string)
  {
    const headers: Headers = new Headers();
    headers.append('Content-type', 'application/json');
    let usertoken = `Bearer ${token}`;
    headers.append('Authorization', usertoken);

    const opts: RequestOptions = new RequestOptions();
    opts.headers = headers;
    return this.http.delete(
      `http://localhost:51432/api/Reservations/DeleteReservation/${id}`
      ,opts
    ).pipe(map((res: Response) => this.extractData(res)));
  }
    //end of reservation section ----------------------------------------------------------------------------
    //comments section ----------------------------------------------------------------------------
    
  
    allRatesService(serviceId :number, token : string )
    {
      const headers: Headers = new Headers();
      headers.append('Content-type', 'application/json');
      let usertoken = `Bearer ${token}`;
      headers.append('Authorization', usertoken);
  
      const opts: RequestOptions = new RequestOptions();
      opts.headers = headers;
  
      return this.http.get(
        `http://localhost:51432/api/Rates/GetAllRatesService/${serviceId}`
        ,opts
      ).pipe(map((res: Response) => this.extractData(res)));
    }
  
    canLeaveComment(userId: number,serviceId :number, token : string )
    {
      const headers: Headers = new Headers();
      headers.append('Content-type', 'application/json');
      let usertoken = `Bearer ${token}`;
      headers.append('Authorization', usertoken);
  
      const opts: RequestOptions = new RequestOptions();
      opts.headers = headers;
  
      return this.http.get(
        `http://localhost:51432/api/Rates/CanLeaveComment?id=${userId}&serviceId=${serviceId}`
        ,opts
      );
    }
  
    editRate(rate : Rate, token: string)
    {
      const headers: Headers = new Headers();
      headers.append('Content-type', 'application/json');
      let usertoken = `Bearer ${token}`;
      headers.append('Authorization', usertoken);
  
      const opts: RequestOptions = new RequestOptions();
      opts.headers = headers;
  
      return this.http.put(
        `http://localhost:51432/api/Rates/PutRate/`
        ,
        JSON.stringify({
          
        }), opts);
    }
  
    deleteRate(rate : Rate, token: string)
    {
      const headers: Headers = new Headers();
      headers.append('Content-type', 'application/json');
      let usertoken = `Bearer ${token}`;
      headers.append('Authorization', usertoken);
  
      const opts: RequestOptions = new RequestOptions();
      opts.headers = headers;
  
      return this.http.delete(
        `http://localhost:51432/api/Rates/DeleteRate/`
        ,opts
      );
    }
  
    //end of comments section ----------------------------------------------------------------------------
    //comments cinema----------------------
    getCinema(): Observable<any>
    {
      const headers: Headers = new Headers();
      headers.append('Accept', 'application/json');
      headers.append('Content-type', 'application/json');
      const opts: RequestOptions = new RequestOptions();
      opts.headers = headers;
      var url = 'http://localhost:8765/bioskopsala-service/cinemas/allCinemas';
      return this.http.get(url, opts).pipe(map((res: Response) => this.extractData(res)));
    }

    getCinemaWithFilter(userOnSession : string, name : string, x : number, y : number, radius : number, isSort : boolean): Observable<any>
    {
      console.log(name, x, y, radius, isSort);
      const headers: Headers = new Headers();
      headers.append('Accept', 'application/json');
      headers.append('Content-type', 'application/json');
      const opts: RequestOptions = new RequestOptions();
      opts.headers = headers;
      return this.http.post(
        `http://localhost:8765/bioskopsala-service/cinemas/search/findGeneral`
        ,
        JSON.stringify({
          userOnSession : userOnSession,
          name : name,
          isSort : isSort,
          radius : radius,
          x:x,
          y:y
        }), opts).pipe(map((res: Response) => this.extractData(res)));
    }

    getAverageMark(id:String)
    {
      const headers: Headers = new Headers();
      headers.append('Accept', 'application/json');
      headers.append('Content-type', 'application/json');
      const opts: RequestOptions = new RequestOptions();
      opts.headers = headers;
      var url = `http://localhost:8765/bioskopsala-service/cinemas/getAverageRate${id}`;
      //return this.http.get(url, opts).pipe(map((res: Response) => this.extractData(res)));
      return this.http.get(
        `http://localhost:8765/bioskopsala-service/cinemas/getAverageRate?id=${id}`
        ,opts
      );
    }

    addNewCinema(cinema : Cinema,username: string):Observable<any>
    {
      const headers: Headers = new Headers();
      headers.append('Accept', 'application/json');
      headers.append('Content-type', 'application/json');
      headers.append('Username', username);

      const opts: RequestOptions = new RequestOptions();
      opts.headers = headers;

      return this.http.post(
        `http://localhost:8765/bioskopsala-service/cinemas/addCinema`
        ,
        JSON.stringify({
          id: cinema.id,
          name: cinema.name,
          street: cinema.street,
          number: cinema.number,
          location: cinema.location,
          rates:cinema.rates,
          ranking:0,
          theaters:cinema.theaters
        }), opts).pipe(map((res: Response) => this.extractData(res)));
    }

    editCinema(cinema : Cinema,username: string):Observable<any>
    {
      const headers: Headers = new Headers();
      headers.append('Accept', 'application/json');
      headers.append('Content-type', 'application/json');
      headers.append('Username', username);

      const opts: RequestOptions = new RequestOptions();
      opts.headers = headers;

      return this.http.put(
        `http://localhost:8765/bioskopsala-service/cinemas/editCinema`
        ,
        JSON.stringify({
          id: cinema.id,
          name: cinema.name,
          street: cinema.street,
          number: cinema.number,
          location: cinema.location,
          rates:cinema.rates,
          ranking:0,
          theaters:cinema.theaters
        }), opts).pipe(map((res: Response) => this.extractData(res)));
    }

    addNewRate(r: Rate,cinemaId:String, username: string):Observable<any>
    {
      const headers: Headers = new Headers();
      headers.append('Accept', 'application/json');
      headers.append('Content-type', 'application/json');
      headers.append('Username', username);
  
      const opts: RequestOptions = new RequestOptions();
      opts.headers = headers;
  
      return this.http.post(
        `http://localhost:8765/bioskopsala-service/cinemas/addRate?id=${cinemaId}`
        ,
        JSON.stringify({
          rate : r
        }), opts).pipe(map((res: Response) => this.extractData(res)));
  
    }

    getCinemaById(id:String):Observable<any>
    {
      const headers: Headers = new Headers();
      headers.append('Accept', 'application/json');
      headers.append('Content-type', 'application/json');
      const opts: RequestOptions = new RequestOptions();
      opts.headers = headers;
      return this.http.get(
        `http://localhost:8765/bioskopsala-service/cinemas/findCinemaById?id=${id}`
        ,opts
      ).pipe(map((res: Response) => this.extractData(res)));
    }

    deleteCinema(cinemaId : String ,username: string)
    {
      const headers: Headers = new Headers();
      headers.append('Accept', 'application/json');
      headers.append('Content-type', 'application/json');
      headers.append('Username', username);

      const opts: RequestOptions = new RequestOptions();
      opts.headers = headers;

      return this.http.delete(
        `http://localhost:8765/bioskopsala-service/cinemas/deleteCinema?id=${cinemaId}`
        , opts);
    }

    //end of cinema section ----------------------------------------------------------------------------
    //comments theater----------------------
    addNewTheater(theater : Theater,cinemaId : string ,username: string)
    {
      const headers: Headers = new Headers();
      headers.append('Accept', 'application/json');
      headers.append('Content-type', 'application/json');
      headers.append('Username', username);

      const opts: RequestOptions = new RequestOptions();
      opts.headers = headers;

      return this.http.post(
        `http://localhost:8765/bioskopsala-service/cinemas/addTheater?id=${cinemaId}`
        ,
        JSON.stringify({
          id: theater.id,
          name: theater.name,
          capacity: theater.capacity,
          theaterType: theater.theaterType,
          chairsPerColumn: theater.chairsPerColumn,
          chairsPerRow:theater.chairsPerRow,
        }), opts);
    }

    editTheater(theater : Theater,cinemaId : string ,username: string):Observable<any>
    {
      const headers: Headers = new Headers();
      headers.append('Accept', 'application/json');
      headers.append('Content-type', 'application/json');
      headers.append('Username', username);

      const opts: RequestOptions = new RequestOptions();
      opts.headers = headers;

      return this.http.put(
        `http://localhost:8765/bioskopsala-service/cinemas/editTheater?idCinema=${cinemaId}`
        ,
        JSON.stringify({
          id: theater.id,
          name: theater.name,
          capacity: theater.capacity,
          theaterType: theater.theaterType,
          chairsPerColumn: theater.chairsPerColumn,
          chairsPerRow:theater.chairsPerRow,
        }), opts).pipe(map((res: Response) => this.extractData(res)));
    }

    getTheater(theaterId : string,cinemaId : string):Observable<any>
    {
      const headers: Headers = new Headers();
      headers.append('Accept', 'application/json');
      headers.append('Content-type', 'application/json');
      const opts: RequestOptions = new RequestOptions();
      opts.headers = headers;
      return this.http.get(
        `http://localhost:8765/bioskopsala-service/cinemas/getTheater?idTheater=${theaterId}&&idCinema=${cinemaId}`
        ,opts
      ).pipe(map((res: Response) => this.extractData(res)));
    }

    deleteTheater(theater : Theater,cinemaId : string ,username: string)
    {
      const headers: Headers = new Headers();
      headers.append('Accept', 'application/json');
      headers.append('Content-type', 'application/json');
      headers.append('Username', username);

      const opts: RequestOptions = new RequestOptions();
      opts.headers = headers;

      return this.http.delete(
        `http://localhost:8765/bioskopsala-service/cinemas/deleteTheater?idTheater=${theater.id}&&idCinema=${cinemaId}`
        , opts);
    }

    
    //end of theater section ----------------------------------------------------------------------------
  }

import { Component, OnInit, OnDestroy, Injectable, Input } from '@angular/core';
import { Vehicle } from '../../../Model/vehicle';
import { Service } from '../../../Model/service';
import { ServiceManager } from '../../../Services/[services].service';
import { AuthService } from '../../../Services/auth.service';
import { HttpService } from '../../../Services/http-service.service';
import { ActivatedRoute } from '@angular/router'; 
import { TypeOfVehicle } from '../../../Model/type-of-vehicle'; 
import { PriceList } from '../../../Model/pricelist';
import { TheaterType } from '../../../Model/theathertype';
import { Cinema } from '../../../Model/cinema';
import { GeoJson } from '../../../Model/geo-json';
import { Rate } from '../../../Model/rate';
import { Theater } from '../../../Model/theather';

@Injectable()
@Component({
  selector: 'app-add-new-car',
  templateUrl: './add-new-car.component.html',
  styleUrls: ['./add-new-car.component.css']
})
export class AddNewCarComponent implements OnInit, OnDestroy {

  types: TheaterType[];
	errorText : string;
	typeNameInput : string;
  typeNameSelected : string;
  typeOfVehicle : TheaterType;
  theater : Theater;
  selectedFile: File[]; 
  cinemaId : string;
  cinema : Cinema;

  private sub : any;

  constructor(private route: ActivatedRoute,private serviceManager : ServiceManager, private authService : AuthService, public httpService: HttpService) {
    this.typeNameInput = "";
    this.typeNameSelected = "";
    this.types = [];
    this.types.push(TheaterType.normal);
    this.types.push(TheaterType.projection3D);
    this.types.push(TheaterType.projection4D);
    this.cinema = new Cinema('','','','',new GeoJson("Point",[45.25024259251935,19.835199103219566]),new Map<string,Rate>(),0,[]);
    this.theater = new Theater('','',0,TheaterType.normal,0,0);
    this.errorText = "";
      this.sub = this.route.params.subscribe(params => {
        this.cinemaId = params['id']; // (+) converts string 'id' to a number
    }); 

    this.serviceManager.getCinemaById(this.cinemaId).subscribe(
      (res: any) => {
        this.cinema = res;
      },
      error =>{
        console.log(error);
     });
   }

  ngOnInit() {
  }

  newCar(){
    
    if(this.theater.name.length == 0 || this.theater.capacity == 0 || this.theater.chairsPerColumn == 0 || this.theater.chairsPerRow == 0 || this.typeNameSelected.length == 0)
    {
       this.errorText = "All fields except picture are requiered";
       return false;
    }
    else
    {
      this.errorText = "";
      for(let i=0; i<this.types.length; i++){
        if(this.types[i] == this.typeNameSelected)
        {
            this.theater.theaterType = this.types[i];
            break;
        }
      } 
    }

    this.serviceManager.addNewTheater(this.theater,this.cinemaId,this.authService.currentUserUsername()).subscribe(
      (res: any) => {
        this.theater = new Theater('','',0,TheaterType.normal,0,0);
        
        alert("Successful added new theater"); 
      },
      error =>{
        console.log(error);
     });
  }

  ngOnDestroy() {
    this.sub.unsubscribe();
  }

  onFileChanged(event) {/*
    if(this.selectedFile == undefined)
    {
      this.selectedFile = [];
    }
    let p : File;
    p = event.target.files[0];
    this.selectedFile.push(p);*/
  }

}

import { Component, OnInit, OnDestroy, SimpleChanges, OnChanges } from '@angular/core';
import { Vehicle } from '../../../Model/vehicle';
import { ServiceManager } from '../../../Services/[services].service';
import { AuthService } from '../../../Services/auth.service';
import { HttpService } from '../../../Services/http-service.service';
import { TypeOfVehicle } from '../../../Model/type-of-vehicle'; 
import { PriceList } from '../../../Model/pricelist';
import { ActivatedRoute } from '@angular/router'; 
import { Theater } from '../../../Model/theather';
import { TheaterType } from '../../../Model/theathertype';

@Component({
  selector: 'app-edit-car',
  templateUrl: './edit-car.component.html',
  styleUrls: ['./edit-car.component.css']
})
export class EditCarComponent implements OnInit,OnChanges,OnDestroy {

  errorText : string;
  types: TheaterType[];
  typeNameInput : string;
  typeNameSelected : string;
  theaterType : TheaterType;
  theater:Theater;
  theaterId : string;
  cinemaId : string;
  private sub : any;

  constructor(private serviceManager : ServiceManager, private authService : AuthService, public httpService: HttpService, private route: ActivatedRoute) {
    this.typeNameInput = "";
    this.typeNameSelected = "";
    this.types = [];
    this.types.push(TheaterType.normal);
    this.types.push(TheaterType.projection3D);
    this.types.push(TheaterType.projection4D);
    this.errorText = "";
    this.theater = new Theater('','',0,TheaterType.normal,0,0);

    this.sub = this.route.params.subscribe(params => {
      this.theaterId = params['id']; // (+) converts string 'id' to a number
   }); 

    /*this.serviceManager.getTheater(this.theaterId,this.cinemaId).subscribe(
      (res: any) => {
        this.theater =  res;
        this.typeNameSelected = this.theater.theaterType.toString();
      },
      error =>{
        console.log(error);
     });*/
    
  }

   ngOnChanges(changes: SimpleChanges) {
    if(changes['theatherId'])
    {
      if(this.theaterId != undefined)
      {

        
      }
      
    }
    
  }

  ngOnInit() {
  }

  onFileChanged(event) {
    //this.selectedFile = event.target.files;
  }
  ngOnDestroy() {
    this.sub.unsubscribe();
  }

  newCar()
  {
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

    /*this.serviceManager.editTheater(this.theater,this.cinemaId,this.authService.currentUserUsername()).subscribe(
      (res: any) => {
        this.theater = new Theater('','',0,TheaterType.normal,0,0);
        
        alert("Successful edited theater"); 
      },
      error =>{
        console.log(error);
     });*/

  }
  
}

  






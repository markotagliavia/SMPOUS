import { Component, OnInit } from '@angular/core';
import { Service } from '../../../Model/service';
import { ServiceManager} from '../../../Services/[services].service';
import { AuthService } from '../../../Services/auth.service'
import { HttpService } from '../../../Services/http-service.service'
import { Cinema } from '../../../Model/cinema';
import { Rate } from '../../../Model/rate';
import { GeoJson, IGeometry, Geometry } from '../../../Model/geo-json';

@Component({
  selector: 'app-add-new-service',
  templateUrl: './add-new-service.component.html',
  styleUrls: ['./add-new-service.component.css']
})
export class AddNewServiceComponent implements OnInit {

  cinema : Cinema;
  errorText : string;
  selectedFile: File; 
  geo:GeoJson;
  koordinata:any;

  constructor(private serviceManager: ServiceManager, private authService: AuthService, private httpService : HttpService) { 
    this.errorText = '';
    this.geo = new GeoJson("Point",[45.25024259251935,19.835199103219566]);
    this.cinema = new Cinema('','','','',this.geo,new Map<string,Rate>(),0,[]);
    var djesTijana =
    {
      Latitude: this.cinema.location.coordinates[0],
      Longitude: this.cinema.location.coordinates[1],
      info : "test",
    }
     this.koordinata = djesTijana;
  }

  ngOnInit() {
  }
  
  changeData()
  {
    if(this.cinema.name.length == 0 || this.cinema.street.length == 0 || this.cinema.number.length == 0)
    {
      this.errorText = "All fields except theaters are requiered";
      
      return false;
    }
    else
    {
          this.errorText = "";
          this.serviceManager.addNewCinema(this.cinema,this.authService.currentUserUsername()).subscribe(

            (res : any) => {

              if(res == true)
              {
                this.geo = new GeoJson("Point",[45.25024259251935,19.835199103219566]);
                this.cinema = new Cinema('','','','',this.geo,new Map<string,Rate>(),0,[]); 
                
                alert("Successful added new cinema"); 
              }
              else
              {
                alert("You don't have permission.");
              }
              
            
            },
            error =>
            {
                    //alert(error.json().Message);
                    return false;
            })
    }
  }

  mapClicked($event: any)
  {
    this.cinema.location.coordinates[0]= $event.coords.lat;
    this.cinema.location.coordinates[1]=$event.coords.lng;
    alert(this.cinema.location.coordinates[0] + " " + this.cinema.location.coordinates[1]);
  }



}

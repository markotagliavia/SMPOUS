import { Component, OnInit, OnDestroy } from '@angular/core';
import { Service } from '../../../Model/service';
import { ServiceManager } from '../../../Services/[services].service';
import { AuthService } from '../../../Services/auth.service';
import { ActivatedRoute } from '@angular/router'; 
import { Cinema } from '../../../Model/cinema';
import { GeoJson } from '../../../Model/geo-json';
import { Rate } from '../../../Model/rate';

@Component({
  selector: 'app-edit-service',
  templateUrl: './edit-service.component.html',
  styleUrls: ['./edit-service.component.css']
})
export class EditServiceComponent implements OnInit, OnDestroy {

  cinema : Cinema;
  errorText : string; 
  cinemaId : string;
  private sub : any;
  

  constructor(private route: ActivatedRoute,private serviceManager : ServiceManager, private authService : AuthService) { 
    this.errorText = '';
    this.cinema = new Cinema('','','','',new GeoJson("Point",[45.25024259251935,19.835199103219566]),new Map<string,Rate>(),0,[]);
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

  onFileChanged(event) {
    //this.selectedFile = event.target.files[0]
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
          this.serviceManager.editCinema(this.cinema,this.authService.currentUserUsername()).subscribe(

            (res : any) => {

              if(res == true)
              {
                
                alert("Successful edited cinema"); 
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

  ngOnDestroy() {
    this.sub.unsubscribe();
  }

}

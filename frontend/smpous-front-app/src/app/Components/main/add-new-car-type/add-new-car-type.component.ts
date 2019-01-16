import { Component, OnInit } from '@angular/core';
import { HttpService } from '../../../Services/http-service.service';
import { AuthService } from '../../../Services/auth.service'; 
import { TypeOfVehicle } from '../../../Model/type-of-vehicle'; 

@Component({
  selector: 'app-add-new-car-type',
  templateUrl: './add-new-car-type.component.html',
  styleUrls: ['./add-new-car-type.component.css']
})
export class AddNewCarTypeComponent implements OnInit {


  constructor(public httpService: HttpService,private authService: AuthService) {
  
  }

  
  ngOnInit() {

    
  }
  
}

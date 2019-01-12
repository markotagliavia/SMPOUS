import { Component, OnInit, Injectable, Input } from '@angular/core';
import { Service } from '../../../Model/service';  
import { Theater } from '../../../Model/theather';
import { Cinema } from '../../../Model/cinema';
import { ServiceManager } from '../../../Services/[services].service';

@Injectable()
@Component({
  selector: 'app-service-unit',
  templateUrl: './service-unit.component.html',
  styleUrls: ['./service-unit.component.css']
})
export class ServiceUnitComponent implements OnInit {

  @Input() cinema : Cinema;
  ocena : number;
  AverageMark:number;

  constructor(public serviceManager: ServiceManager) { 
  }

  ngOnInit() {
    this.serviceManager.getAverageMark(this.cinema.id).subscribe(
      (res: any) => {
        this.AverageMark = Number(res.text());
        console.log(this.AverageMark);
        if(this.AverageMark >= 0 && this.AverageMark <= 0.5)
        {
          this.ocena = 0;
        }
        else if(this.AverageMark >= 0.5 && this.AverageMark < 1.5)
        {
          this.ocena = 1;
        }
        else if(this.AverageMark >= 1.5 && this.AverageMark < 2.5)
        {
          this.ocena = 2;
        }
        else if(this.AverageMark >= 2.5 && this.AverageMark < 3.5)
        {
          this.ocena = 3;
        }
        else if(this.AverageMark >= 3.5 && this.AverageMark < 4.5)
        {
          this.ocena = 4;
        }
        else if(this.AverageMark >= 4.5 && this.AverageMark <= 5)
        {
          this.ocena = 5;
        }
     },
        error =>{
          console.log(error);
        });
    
  

    
    
  }

}

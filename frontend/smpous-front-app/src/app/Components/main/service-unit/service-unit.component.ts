import { Component, OnInit, Injectable, Input } from '@angular/core';
import { Service } from '../../../Model/service';  
import { Theater } from '../../../Model/theather';
import { Cinema } from '../../../Model/cinema';
import { ServiceManager } from '../../../Services/[services].service';
import { Rate } from '../../../Model/rate';

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
  Num : number;

  constructor(public serviceManager: ServiceManager) { 
  }

  ngOnInit() {
    
        this.AverageMark = 0;
        var sum = 0;
        if(Object.keys(this.cinema.rates).length != 0)
        {
          for (const [key, value] of Object.entries(this.cinema.rates)) { 
            if(value == "one")
            {
              this.Num = 1;
            }
            else if(value == "two")
            {
              this.Num = 2;
            }
            else if(value == "three")
            {
              this.Num = 3;
            }
            else if(value == "four")
            {
              this.Num = 4;
            }
            else
            {
              this.Num = 5;
            }
            sum = sum + this.Num;
            this.AverageMark = sum / Object.keys(this.cinema.rates).length;
             console.log(this.AverageMark);
        }
        
          
        }
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
  
  }

}

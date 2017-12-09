import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';


import { Observable } from 'rxjs/Observable';
import { Router, ActivatedRoute } from '@angular/router';

import { PriceService } from "./price.service";
import { PriceListItem } from './price-list-item';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-price-list',
  templateUrl: './price-list.component.html',
  styleUrls: ['./price-list.component.css']
})
export class PriceListComponent implements OnInit {
    priceListItems: PriceListItem[];

    constructor( 
        private priceService: PriceService,
        private router: Router,
        private route: ActivatedRoute,
    ){ 
    }

    ngOnInit() {
        this.findAll();
    }

    findAll() {
        this.priceService.findLast()
          .subscribe(priceListItems => 
               this.priceListItems = priceListItems);
    }

    save(form: NgForm) {
        var list = form.value;
        var temp = [];
        Object.keys(list).forEach(function(key,index) {
            temp.push(parseFloat(list[key]));              
        });
        this.priceService.addPriceList(temp)
          .subscribe(
              x=> {
                if(x)
                    this.router.navigate(['/factors']) 

          })
    }
}

import { Component, OnInit, Input } from '@angular/core';
import { Http, Response } from "@angular/http";
import { FormControl, FormGroup } from "@angular/forms";

import { Observable } from 'rxjs/Observable';
import { of } from 'rxjs/observable/of';

import { FactorService } from "../factor.service";
import { Factor } from '../factor';

@Component({
    selector: 'app-factor-list',
    templateUrl: './factor-list.component.html',
    styleUrls: ['./factor-list.component.css'],
})
export class FactorListComponent implements OnInit {
    title = "Factors";
    factors : Array<Factor>;
    constructor(
        private factorService: FactorService,
        private http: Http
    ) { }

	ngOnInit() { 
        this.factorService.findAll()
            .subscribe(factors => 
                this.factors = factors);

        this.factorService.newFactor.subscribe(
            data =>  {
                var updateItem = this.factors.find(x=>x.id == data.id);
                var index = this.factors.indexOf(updateItem);

                if(index == -1)
                    this.factors = [data, ...this.factors]
                else
                    this.factors[index] = data;
          }) 
    }
    
    delete(factor){
      var index = this.factors.indexOf(factor);
      this.factors.splice(index, 1);

      this.factorService.deleteById(factor.id)
        .subscribe(null,
          err => {
            alert("Could not delete factor.");
            this.factors.splice(index, 0, factor);
          });
    }
}

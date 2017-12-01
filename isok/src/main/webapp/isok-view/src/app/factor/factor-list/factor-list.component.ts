import { Component, OnInit, Input } from '@angular/core';
import { Http, Response } from "@angular/http";
import { FormControl, FormGroup } from "@angular/forms";

import { Observable } from 'rxjs/Observable';
import { of } from 'rxjs/observable/of';
import { Router, ActivatedRoute } from '@angular/router';

import { FactorService } from "../factor.service";
import { Factor } from '../factor';

import { CategoryFactorService } from "../../category-factor/category-factor.service";
import { CategoryFactor } from "../../category-factor/category-factor";

@Component({
    selector: 'app-factor-list',
    templateUrl: './factor-list.component.html',
    styleUrls: ['./factor-list.component.css'],
})
export class FactorListComponent implements OnInit {
    title = "Factors";
    factors : Array<Factor>;
    categories : Array<CategoryFactor>;
    categoryForFactors;
    constructor(
        private factorService: FactorService,
        private categoryFactorService: CategoryFactorService,
        private http: Http,
        private route: ActivatedRoute
    ) { }

	ngOnInit() { 
        this.factorService.findAll()
            .subscribe(factors => 
                this.factors = factors
            );

        this.categoryFactorService.findAll()
            .subscribe(categories => 
                this.categories = categories
            );

        this.factorService.newFactor.subscribe(
            data =>  {
                var updateItem = this.factors.find(x=>x.id == data.id);
                var index = this.factors.indexOf(updateItem);
                if(this.categoryForFactors.value.split(":")[1] == undefined || 
                        parseInt(this.categoryForFactors.value.split(":")[1]) == 0 || 
                        data.category == parseInt(this.categoryForFactors.value.split(":")[1])) {
                    if(index == -1)
                        this.factors = [data, ...this.factors]
                    else
                        this.factors[index] = data;
                }
            }
        ) 

        this.route.params.subscribe(params => {
            var id = params['categoryFactorId'];
            if (!id)
                return; 
            this.update(id); 
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

    update(catID) {
        if(catID == undefined) {
            this.categoryFactorService.findFactorsByID(this.categoryForFactors.value.split(":")[1])
                .subscribe(factors => 
                    this.factors = factors
                )
        } else {
            this.categoryFactorService.findFactorsByID(parseInt(catID))
                .subscribe(factors => 
                    this.factors = factors
                )         
        }
    }
}

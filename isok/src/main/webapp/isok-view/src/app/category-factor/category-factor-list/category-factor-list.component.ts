import { Component, OnInit, Input } from '@angular/core';
import { Http, Response } from "@angular/http";
import { FormControl, FormGroup, } from "@angular/forms";

import { Observable } from 'rxjs/Observable';
import { of } from 'rxjs/observable/of';

import {ActivatedRoute, Router} from '@angular/router';

import { CategoryFactorService } from "../category-factor.service";
import { CategoryFactor } from '../category-factor';

@Component({
    selector: 'app-category-factor-list',
    templateUrl: './category-factor-list.component.html',
    styleUrls: ['./category-factor-list.component.css']
})
export class CategoryFactorListComponent implements OnInit {
	  title = "Category factors";
    categoryFactors : Array<CategoryFactor>;
	  constructor(
        private categoryFactorService: CategoryFactorService,
        private http: Http,
        private router: Router
    ) { }

   	ngOnInit() { 
        this.categoryFactorService.findAll()
            .subscribe(categoryFactors => 
                this.categoryFactors = categoryFactors);

        this.categoryFactorService.newCategoryFactor.subscribe(
            data =>  {
                var updateItem = this.categoryFactors.find(x=>x.id == data.id);
                var index = this.categoryFactors.indexOf(updateItem);

                if(index == -1)
                    this.categoryFactors = [data, ...this.categoryFactors]
                else
                    this.categoryFactors[index] = data;
          }) 
    }
    
    delete(factor){
      var index = this.categoryFactors.indexOf(factor);
      this.categoryFactors.splice(index, 1);

      this.categoryFactorService.deleteById(factor.id)
        .subscribe(null,
          err => {
            alert("Could not delete category factor.");
            this.categoryFactors.splice(index, 0, factor);
          });
    }

    getFactors(factor) {
        this.router.navigate(['/factors', { categoryFactorId: factor.id}]);
    }
}

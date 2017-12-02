import { Component, OnInit, Input } from '@angular/core';
import { Http, Response } from "@angular/http";

import { Observable } from 'rxjs/Observable';
import { Router, ActivatedRoute } from '@angular/router';

import { CategoryFactorService } from "./category-factor.service";
import { CategoryFactor } from './category-factor';

@Component({
    selector: 'app-category-factor',
    templateUrl: './category-factor.component.html',
    styleUrls: ['./category-factor.component.css']
})
export class CategoryFactorComponent implements OnInit {

	  categoryFactors: CategoryFactor[];
    categoryFactor : CategoryFactor = new CategoryFactor();

  	constructor(
          private categoryFactorService: CategoryFactorService,
          private router: Router,
          private route: ActivatedRoute

          )
  	{ }

  	ngOnInit() {  
  	  	this.findAll();

        this.route.params.subscribe(params => {
            var id = params['id'];
            if (!id)
              return;

            this.categoryFactorService.get(id)
                .subscribe(
                    categoryFactor => this.categoryFactor = categoryFactor,
                );
            this.findAll();     
        })   
  	}	

    findAll() {
        this.categoryFactorService.findAll()
            .subscribe(categoryFactors => 
                this.categoryFactors = categoryFactors);
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

  	get(categoryFactor) {
        this.router.navigate(['/categoryFactors/'+ categoryFactor.id]); 
    }

    save() {
        if(this.categoryFactor.name == undefined)
            return;
        if(this.categoryFactor.id == undefined) {
            this.categoryFactorService.save(this.categoryFactor)
                .subscribe(categoryFactor => {
                    this.categoryFactor = categoryFactor;  
                    this.findAll(); 
            })
        }  
        else {
            this.categoryFactorService.update(this.categoryFactor)
                .subscribe(categoryFactor => {
                    this.categoryFactor = categoryFactor;
                    this.findAll(); 
                    this.get(this.categoryFactor);
                })         
        }
    }

    getFactors(categoryFactor) {
        //this.router.navigate(['/categoryFactors/findFactorsByID?findFactorsByID='+ categoryFactor.id]); 
        this.router.navigate(['/factors', { categoryFactorId: categoryFactor.id}]);
    }
}

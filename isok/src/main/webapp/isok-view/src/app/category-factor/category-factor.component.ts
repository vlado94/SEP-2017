import { Component, OnInit, Input } from '@angular/core';
import { Http, Response } from "@angular/http";

import { Observable } from 'rxjs/Observable';
import { Router, ActivatedRoute } from '@angular/router';
import { ToastsManager } from 'ng2-toastr/ng2-toastr';
import { ViewContainerRef } from '@angular/core';

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
          public toastr: ToastsManager,vcr: ViewContainerRef,
          private categoryFactorService: CategoryFactorService,
          private router: Router,
          private route: ActivatedRoute
          )
  	{ 
        this.toastr.setRootViewContainerRef(vcr);
    }

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
          	.subscribe(
                data => {
                    this.toastr.success('Done!', 'Success');
                },
             		err => {
                 		this.toastr.error("Could not delete category factor, becouse some factors are contected with that.",'Error');
                 		this.categoryFactors.splice(index, 0, factor);
               	}
            );
  	}

  	get(categoryFactor) {
        this.router.navigate(['/categoryFactors/'+ categoryFactor.id]); 
    }

    save() {
        if(this.categoryFactor.name == undefined)
            return;
        if(this.categoryFactor.id != undefined) {
            this.categoryFactorService.update(this.categoryFactor)
                .subscribe(categoryFactor => {
                    this.categoryFactor = categoryFactor;
                    this.findAll(); 
                    this.get(this.categoryFactor);
                    this.toastr.success('Done!', 'Success');
                })         
        }
    }

    getFactors(categoryFactor) {
        this.router.navigate(['/factors', { categoryFactorId: categoryFactor.id}]);
    }
}

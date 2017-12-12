import { Component, OnInit, Input } from '@angular/core';
import { Http, Response } from "@angular/http";

import { Observable } from 'rxjs/Observable';
import { Router, ActivatedRoute } from '@angular/router';
import { ToastsManager } from 'ng2-toastr/ng2-toastr';
import { ViewContainerRef } from '@angular/core';

import { FactorService } from "./factor.service";
import { Factor } from './factor';

import { CategoryFactorService } from "./../category-factor/category-factor.service";
import { CategoryFactor } from "./../category-factor/category-factor";

@Component({
    selector: 'app-factor',
    templateUrl: './factor.component.html',
    styleUrls: ['./factor.component.css']
})
export class FactorComponent implements OnInit {
    factors: Factor[];
    categories: CategoryFactor[];
    factor : Factor = new Factor();
    
  	constructor(
          public toastr: ToastsManager,vcr: ViewContainerRef,
          private categoryFactorService: CategoryFactorService,
          private factorService: FactorService,
          private router: Router,
          private route: ActivatedRoute
        )
  	{ 
        this.toastr.setRootViewContainerRef(vcr);
    }

  	ngOnInit() {  
  	 
        this.categoryFactorService.findAll()
            .subscribe(categories => 
                this.categories = categories);

        this.route.params.subscribe(params => {
            var id = params['id'];
            if (!id)
              return;

            this.factorService.get(id)
                .subscribe(
                    factor => this.factor = factor,
                );
        })   
  	}	

    findAll() {
        this.factorService.findAll()
            .subscribe(factors => 
                this.factors = factors);
    }

  	delete(factor){
        var index = this.factors.indexOf(factor);
      	this.factors.splice(index, 1);

        this.factorService.deleteById(factor.id)
          	.subscribe(
                data => {
                    this.toastr.success('Done!', 'Success');
                },
                err => {
                   this.toastr.error("Could not delete factor, becouse some policy are contected with that.",'Error');
                   this.factors.splice(index, 0, factor);
               }
            );
  	}

  	get(factor) {
        this.router.navigate(['/factors/'+ factor.id]); 
    }

    save() {
        if(this.factor.name == undefined)
            return;
        var validInput = true;
        if(this.factor.category != 2) {
            if(this.factor.id == undefined) {
                this.factorService.save(this.factor)
                    .subscribe(factor => {
                        this.factor = factor;  
                        this.findAll();
                        this.toastr.success('Done!', 'Success');
                })
            }  
            else {
                this.factorService.update(this.factor)
                    .subscribe(factor => {
                        this.factor = factor;
                        this.findAll();
                        this.get(this.factor);
                        this.toastr.success('Done!', 'Success');
                    })         
            }
        }
        else {
            this.toastr.error("Can't change factors for choosen category!", 'Error');  
        }
    }

    findByCategory(catID) {
        this.factorService.findByCategory(parseInt(catID))
            .subscribe(factors => 
                  this.factors = factors
            )    
        return catID;     
    }
}

import { Component, OnInit, Input,ViewChild  } from '@angular/core';
import { Http, Response } from "@angular/http";
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';

import { CategoryFactorService } from "../category-factor.service";
import { CategoryFactor } from "../category-factor";

@Component({
    selector: 'app-category-factor-form',
    templateUrl: './category-factor-form.component.html',
    styleUrls: ['./category-factor-form.component.css']
})
export class CategoryFactorFormComponent implements OnInit {
	categoryFactorForm: FormGroup;
    categoryFactor : CategoryFactor  = new CategoryFactor();
    factorValue : CategoryFactor  = new CategoryFactor();
    title: string;

	constructor(
        formBuilder: FormBuilder,
        private categoryFactorService: CategoryFactorService,
        private route: ActivatedRoute,
        private http: Http) {
            this.categoryFactorForm = formBuilder.group({
                id : [''],
                name: ['', [
                    Validators.required,
                    Validators.minLength(3)
                ]],
            });
    }

  	ngOnInit() {
	    var id = this.route.params.subscribe(params => {
            var id = params['id'];
            this.title = id ? 'Edit category factor' : 'New category factor';
            if (!id)
              return;

            this.categoryFactorService.get(id)
                .subscribe(
                    categoryFactor => this.categoryFactor = categoryFactor,
                );
        });

    }

  	save(data) {
        var factorValue = this.categoryFactorForm.value;
        if (factorValue.id !== undefined){
            this.categoryFactorService.update(factorValue);
        } else {
            this.categoryFactorService.save(factorValue);
        }
    }   
}

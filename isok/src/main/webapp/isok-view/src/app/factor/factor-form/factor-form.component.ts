import { Component, OnInit, Input,ViewChild  } from '@angular/core';
import { Http, Response } from "@angular/http";
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';

import { FactorService } from "../factor.service";
import { CategoryFactorService } from "../../category-factor/category-factor.service";
import { Factor } from "../factor";
import { CategoryFactor } from "../../category-factor/category-factor";

@Component({
  	selector: 'app-factor-form',
    templateUrl: './factor-form.component.html',
    styleUrls: ['./factor-form.component.css'],
})
export class FactorFormComponent implements OnInit {
    factorForm: FormGroup;
    factor : Factor  = new Factor();
    title: string;
    categories : Array<CategoryFactor>;

	constructor(
        formBuilder: FormBuilder,
        private factorService: FactorService,
        private categoryFactorService: CategoryFactorService,
        private route: ActivatedRoute,
        private http: Http) {
            this.factorForm = formBuilder.group({
                id : [''],
                name: ['', [
                    Validators.required,
                    Validators.minLength(3)
                ]],
                category : ['',[
                    Validators.required]]
            });
    }

    ngOnInit() {
        this.categoryFactorService.findAll()
            .subscribe(categories => 
                this.categories = categories
            );

	    this.route.params.subscribe(params => {
            var id = params['id'];
            this.title = id ? 'Edit factor' : 'New factor';
            if (!id)
              return;

            this.factorService.get(id)
                .subscribe(
                    factor => this.factor = factor,
                );
        });
    }

  	save(data) {
        var factorValue = this.factorForm.value;
        if (factorValue.id !== undefined){
            this.factorService.update(factorValue);
        } else {
            this.factorService.save(factorValue);
        }
    }
}

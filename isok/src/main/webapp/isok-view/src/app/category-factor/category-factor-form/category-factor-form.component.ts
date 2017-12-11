import { Component, OnInit, Input  } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';

import { CategoryFactorComponent } from "../category-factor.component";

@Component({
    selector: 'app-category-factor-form',
    templateUrl: './category-factor-form.component.html',
    styleUrls: ['./category-factor-form.component.css']
})
export class CategoryFactorFormComponent implements OnInit {
	categoryFactorForm: FormGroup;

	constructor(
        formBuilder: FormBuilder,
        private router: Router,
        private categoryFactorComponent: CategoryFactorComponent
    ) {
        this.categoryFactorForm = formBuilder.group({
            id : [''],
            name: ['', [
                Validators.required,
                Validators.minLength(3)
            ]],
            basePrice: ['', [
                Validators.required
            ]],
        });
    }

  	ngOnInit() {
    }

    resetForm() {
        this.router.navigate(['/categoryFactors']); 
    }
}

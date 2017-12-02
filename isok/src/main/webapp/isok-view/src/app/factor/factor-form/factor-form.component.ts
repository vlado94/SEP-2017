import { Component, OnInit, Input  } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';

import { FactorComponent } from "../factor.component";

@Component({
  	selector: 'app-factor-form',
    templateUrl: './factor-form.component.html',
    styleUrls: ['./factor-form.component.css'],
})
export class FactorFormComponent implements OnInit {
    factorForm: FormGroup;

	constructor(
        formBuilder: FormBuilder,
        private router: Router,
        private factorComponent: FactorComponent) {
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
    }

    resetForm() {
        this.router.navigate(['/factors']); 
    }
}

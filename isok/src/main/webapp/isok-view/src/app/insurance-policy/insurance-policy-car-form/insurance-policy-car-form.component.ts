import {Component, Input, Output, EventEmitter} from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';

import { NgModule } from '@angular/core';


@Component({
    selector: 'app-insurance-policy-car-form',
    templateUrl: './insurance-policy-car-form.component.html',
    styleUrls: ['./insurance-policy-car-form.component.css']
})

@NgModule({
    imports: [
    ],
    exports: [ // components that we want to make available
    ],
    declarations: [ // components for use in THIS module
    ],
    providers: [ // singleton services

    ]
})

export class InsurancePolicyCarForm {
    insurancePolicyCarForm: FormGroup;
    constructor() {
        this.insurancePolicyCarForm = new FormGroup({
            duration: new FormControl('', [
                Validators.required,
                Validators.pattern("[0-9]*")]),
            paket: new FormControl('', [
                Validators.required]),
            vehicle: new FormControl('', [Validators.required]),
            typeOfVehicle: new FormControl('', [Validators.required]),
            year: new FormControl('', [Validators.required, Validators.pattern("[0-9]{4}")]),
            registrationNumber: new FormControl('', [Validators.required]),
            chassisNumber: new FormControl('', [Validators.required]),
            firstName: new FormControl('', [Validators.required]),
            lastName: new FormControl('', [Validators.required]),
            jmbg: new FormControl('', [
                Validators.required,
                Validators.minLength(13),
                Validators.maxLength(13)
            ]),
        })
    }



    ngOnInit() {
        this.insurancePolicyCarForm.reset();

    }
}
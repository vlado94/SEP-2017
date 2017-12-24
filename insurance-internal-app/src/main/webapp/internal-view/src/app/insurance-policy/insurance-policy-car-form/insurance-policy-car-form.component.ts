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
    @Output() setInsurancePolicyCar = new EventEmitter<InsurancePolicyCar>();
    @Output() hideForm = new EventEmitter<string>();

    current: InsurancePolicyCar = null;
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

    @Input()
    set insurancePolicyCar(value: InsurancePolicyCar) {
        this.current = value;
        console.log("SETTOVANJE POLISE ZA AUTO");
        if (value) {
            this.insurancePolicyCarForm.setValue({
                duration: value.duration,
                paket: value.paket,
                vehicle: value.vehicle,
                typeOfVehicle: value.typeOfVehicle,
                year: value.year,
                registrationNumber: value.registrationNumber,
                chassisNumber: value.chassisNumber,
                firstName: value.firstName,
                lastName: value.lastName,
                jmbg: value.jmbg
            })
        }
        else {
            this.insurancePolicyCarForm.reset();
        }
    }

    set(value) {
        var policyCar: InsurancePolicyCar = null;
        if (value != null) {
            policyCar = new InsurancePolicyCar(value.duration, value.paket, value.vehicle, value.typeOfVehicle, value.year, value.registrationNumber, value.chassisNumber, value.firstName, value.lastName, value.jmbg);
            this.setInsurancePolicyCar.emit(policyCar);
        } else {
            this.setInsurancePolicyCar.emit(null);
        }

        this.insurancePolicyCarForm.reset();
    }
    
    closeForm(){
        this.hideForm.emit(null);    
    }
}

export class InsurancePolicyCar {
    duration: number;
    paket: string;
    vehicle: string;
    typeOfVehicle: string;
    year: number;
    registrationNumber: string;
    chassisNumber: string;
    firstName: string;
    lastName: string;
    jmbg: string;

    constructor(duration: number,
        paket: string,
        vehicle: string,
        typeOfVehicle: string,
        year: number,
        registrationNumber: string,
        chassisNumber: string,
        firstName: string,
        lastName: string,
        jmbg: string) {
        this.duration = duration;
        this.paket = paket;
        this.vehicle = vehicle;
        this.typeOfVehicle = typeOfVehicle;
        this.year = year;
        this.registrationNumber = registrationNumber;
        this.chassisNumber = chassisNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.jmbg = jmbg;
    }

}
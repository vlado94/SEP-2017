import {Component, Input, Output, EventEmitter} from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';

import { NgModule } from '@angular/core';


@Component({
    selector: 'app-insurance-policy-home-form',
    templateUrl: './insurance-policy-home-form.component.html',
    styleUrls: ['./insurance-policy-home-form.component.css']
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

export class InsurancePolicyHomeForm {
    insurancePolicyHomeForm: FormGroup;
    @Output() setInsurancePolicyHome = new EventEmitter<InsurancePolicyHome>();

    @Output() hideForm = new EventEmitter<string>();
    current: InsurancePolicyHome = null;

    constructor() {
        this.insurancePolicyHomeForm = new FormGroup({
            duration: new FormControl('', [
                Validators.required,
                Validators.pattern("[0-9]*")]),
            size: new FormControl('', [
                Validators.required]),
            age: new FormControl('', [
                Validators.required]),
            value: new FormControl('', [
                Validators.required]),
            risk: new FormControl('', [
                Validators.required]),
            address: new FormControl('', [
                Validators.required]),
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
        this.insurancePolicyHomeForm.reset();

    }

    @Input()
    set insurancePolicyHome(value: InsurancePolicyHome) {
        this.current = value;
        console.log("SETTOVANJE POLISE ZA KUCU");
        if (value) {
            this.insurancePolicyHomeForm.setValue({
                duration: value.duration,
                size: value.size,
                age: value.age,
                risk: value.risk,
                value: value.value,
                address: value.address,
                firstName: value.firstName,
                lastName: value.lastName,
                jmbg: value.jmbg
            })
        }
        else {
            this.insurancePolicyHomeForm.reset();
        }
    }

    set(value) {
        var policyHome: InsurancePolicyHome = null;
        if (value != null) {
            policyHome = new InsurancePolicyHome(value.duration, value.size, value.age, value.value, value.risk, value.address, value.firstName, value.lastName, value.jmbg);
            this.setInsurancePolicyHome.emit(policyHome);
        } else {
            this.setInsurancePolicyHome.emit(null);
        }

        this.insurancePolicyHomeForm.reset();
    }

    closeForm() {
        this.hideForm.emit(null);
    }
}



export class InsurancePolicyHome {
    duration: number;
    size: string;
    age: string;
    value: string;
    risk: string;
    address: string;
    firstName: string;
    lastName: string;
    jmbg: string;
    constructor(duration: number, size: string, age: string, value: string, risk: string,
        address: string, firstName: string, lastName: string, jmbg: string) {
        this.duration = duration;
        this.size = size;
        this.age = age;
        this.value = value;
        this.risk = risk;
        this.address = address;
        this.firstName = firstName;
        this.lastName = lastName;
        this.jmbg = jmbg;

    }
}
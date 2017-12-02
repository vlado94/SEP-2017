import {Component, OnInit, Input} from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {Router} from '@angular/router';
import { NgModule } from '@angular/core';

import { FactorService } from "../../factor/factor.service";
import { Factor } from '../../factor/factor';

import {InsurancePolicyService} from '../insurance-policy.service';
//import {InsurancePolicyPersonRequest} from './insurance-policy-person-request';

@Component({
    selector: 'app-insurance-policy-person-form',
    templateUrl: './insurance-policy-person-form.component.html',
    styleUrls: ['./insurance-policy-person-form.component.css']

})
@NgModule({
    imports: [
    ],
    exports: [ // components that we want to make available
    ],
    declarations: [ // components for use in THIS module
    ],
    providers: [ // singleton services
        InsurancePolicyService,
        FactorService
    ]
})
export class InsurancePolicyPersonFormComponent {

    insurancePolicyPerson: FormGroup;
    persons: InsurancePolicyPersonRequest[];
    public submitted: boolean;
    sports: Factor[];
    ages: Factor[];

    constructor(private insurancePolicyService: InsurancePolicyService, private factorService: FactorService) { }

    ngOnInit() {
        this.insurancePolicyPerson = new FormGroup({
            firstName: new FormControl('', [
                Validators.required,
                Validators.minLength(3)
            ]),
            lastName: new FormControl('', [
                Validators.required,
                Validators.minLength(3)
            ]),
            jmbg: new FormControl('', [
                Validators.required,
                Validators.minLength(13),
                Validators.maxLength(13)
            ]),
            passportNumber: new FormControl(''),
            address: new FormControl('', [
                Validators.required
            ]),
            phone: new FormControl('', [
                Validators.required
            ]),
            age: new FormControl('', [
                Validators.required
            ]),
            sport: new FormControl('', [
                Validators.required
            ]),
            amount: new FormControl('', [
                Validators.required
            ]),
        })
        this.factorService.findByCategory(2)
            .subscribe(ages => {
                this.ages = ages;
            })
        this.factorService.findByCategory(1)
            .subscribe(sports => {
                this.sports = sports;
            })
    }

    onSubmit({value}: { value: InsurancePolicyPersonRequest }) {
        this.insurancePolicyService.add(value);
    }
    closePersonForm() {
        this.insurancePolicyService.changePersonFormVisibility(false);
    }
}
export class InsurancePolicyPersonRequest {
    firstName: string;
    lastName: string;
    jmbg: string;
    passportNumber: string;
    address: string;
    phone: string;
    ageId: string;
    sportId: string;
    amountId: string;
    constructor(firstName: string,
        lastName: string,
        jmbg: string,
        passportNumber: string,
        address: string,
        phone: string,
        ageId: string,
        sportId: string,
        amountId: string) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.jmbg = jmbg;
        this.passportNumber = passportNumber;
        this.address = address;
        this.phone = phone;
        this.ageId = ageId;
        this.sportId = sportId;
        this.amountId = amountId;
    }
}
import {Component, OnInit, Input} from '@angular/core';
import {FormControl, FormGroup} from '@angular/forms';
import {Router} from '@angular/router';
import { NgModule } from '@angular/core';

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
    ]
})
export class InsurancePolicyPersonFormComponent {

    insurancePolicyPerson: FormGroup;
    persons: InsurancePolicyPersonRequest[];
    public submitted: boolean;

    constructor(private insurancePolicyService: InsurancePolicyService) { }

    ngOnInit() {
        this.insurancePolicyPerson = new FormGroup({
            firstName: new FormControl(''),
            lastName: new FormControl(''),
            jmbg: new FormControl(''),
            passportNumber: new FormControl(''),
            address: new FormControl(''),
            phone: new FormControl(''),
            age: new FormControl(''),
            sport: new FormControl(''),
            amount: new FormControl(''),
        })
        
    }

    onSubmit({value}: { value: InsurancePolicyPersonRequest }) {
        this.insurancePolicyService.add(value);
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
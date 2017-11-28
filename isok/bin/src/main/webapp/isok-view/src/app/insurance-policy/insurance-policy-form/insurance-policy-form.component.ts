import {Component, OnInit, Input} from '@angular/core';
import {FormControl, FormGroup} from '@angular/forms';
import {Router} from '@angular/router';


@Component({
    selector: 'app-insurance-policy-form',
    templateUrl: './insurance-policy-form.component.html',
    styleUrls: ['./insurance-policy-form.component.css']
})

export class InsurancePolicyFormComponent {

    insurancePolicy: FormGroup;
    public submitted: boolean;
    request: InsurancePolicyRequest;

    ngOnInit() {
        this.insurancePolicy = new FormGroup({
            startDate : new FormControl(''),
            endDate : new FormControl(''),
            duration : new FormControl(''),
            region : new FormControl(''),
            amount: new FormControl(''),
        })
    }


}


export class InsurancePolicyRequest {
    firstName: string;
    lastName: string;

    constructor(firstName: string, lastName: string) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
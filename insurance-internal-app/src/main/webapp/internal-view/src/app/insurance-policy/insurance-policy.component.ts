import {Component} from '@angular/core';
import {InsurancePolicyRequest} from './insurance-policy-form/insurance-policy-form.component';

import {InsurancePolicyPersonRequest} from './insurance-policy-person/insurance-policy-person-form/insurance-policy-person-form.component';
import {InsurancePolicyCar} from './insurance-policy-car-form/insurance-policy-car-form.component';
import {InsurancePolicyHome} from './insurance-policy-home-form/insurance-policy-home-form.component';

@Component({
    selector: 'app-insurance-policy',
    templateUrl: './insurance-policy.component.html',
    styleUrls: ['./insurance-policy.component.css']
})

export class InsurancePolicyComponent {

    activeTab: string = '1';
    personsList: InsurancePolicyPersonRequest[] = [];
    currentInsurancePolicy: InsurancePolicyRequest;
    age: Age = new Age(0, 0, 0);
    insurancePolicyCar: InsurancePolicyCar = null;
    insurancePolicyHome: InsurancePolicyHome = null;

    nextTab(value: string) {
        this.activeTab = value;
    }

    onSubmit(insurancePolicyRequest: InsurancePolicyRequest) {
        this.currentInsurancePolicy = insurancePolicyRequest;
        this.age.firstCategory = insurancePolicyRequest.firstAgeCategory;
        this.age.secondCategory = insurancePolicyRequest.secondAgeCategory;
        this.age.thirdCategory = insurancePolicyRequest.thirdAgeCategory;
    }

    insurancePolicyCarChanged(value) {
        console.log("12334567876543456543");
        if (value && value.registrationNumber != null)
            this.insurancePolicyCar = value;
        else {
            this.insurancePolicyCar = null;
        }
    }

    insurancePolicyHomeChanged(value) {
        console.log("12334567876543456543");
        if (value && value.address != null)
            this.insurancePolicyHome = value;
        else {
            this.insurancePolicyHome = null;
        }
    }
}
export class Age {
    firstCategory: number;
    secondCategory: number;
    thirdCategory: number;
    constructor(firstCategory: number, secondCategory: number, thirdCategory: number) {
        this.firstCategory = firstCategory;
        this.secondCategory = secondCategory;
        this.thirdCategory = thirdCategory;
    }
}
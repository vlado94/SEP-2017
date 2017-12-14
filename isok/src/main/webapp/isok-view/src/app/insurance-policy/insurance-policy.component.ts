import {Component} from '@angular/core';
import {InsurancePolicyRequest} from './insurance-policy-form/insurance-policy-form.component';

import {InsurancePolicyPersonRequest} from './insurance-policy-person/insurance-policy-person-form/insurance-policy-person-form.component';


@Component({
    selector: 'app-insurance-policy',
    templateUrl: './insurance-policy.component.html',
    styleUrls: ['./insurance-policy.component.css']
})

export class InsurancePolicyComponent {

    activeTab: string = '2';
    personsList: InsurancePolicyPersonRequest[] = [];
    currentInsurancePolicy: InsurancePolicyRequest;
    age: Age = new Age(0, 0, 0);
    nextTab(value: string) {
        this.activeTab = value;
    }
 
    onSubmit(insurancePolicyRequest:InsurancePolicyRequest){ 
        this.currentInsurancePolicy = insurancePolicyRequest;
        this.age.firstCategory = insurancePolicyRequest.firstAgeCategory;
        this.age.secondCategory = insurancePolicyRequest.secondAgeCategory;
        this.age.thirdCategory = insurancePolicyRequest.thirdAgeCategory;
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
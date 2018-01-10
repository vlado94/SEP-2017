import { Component, OnInit } from '@angular/core';
import { InsurancePolicyRequest } from './insurance-policy-request'
import { InsurancePolicyPersonRequest } from './insurance-policy-person-request';

@Component({
	selector: 'app-insurance-policy',
	templateUrl: './insurance-policy.component.html',
	styleUrls: ['./insurance-policy.component.scss']
})
export class InsurancePolicyComponent implements OnInit {

	active_tab: number;
	currentInsurancePolicy: InsurancePolicyRequest;
	currentPerson: InsurancePolicyPersonRequest;
	age: Age = new Age(0, 0, 0);

	constructor() { }

	ngOnInit() {
		this.active_tab = 1;
	}

	changeTab(value: number){
		console.log("promeni tab")
		this.active_tab = value;
	}

	savePolicyRequest(insurancePolicyRequest: InsurancePolicyRequest){
		this.currentInsurancePolicy = insurancePolicyRequest;
		this.age.firstCategory = insurancePolicyRequest.firstAgeCategory;
        this.age.secondCategory = insurancePolicyRequest.secondAgeCategory;
        this.age.thirdCategory = insurancePolicyRequest.thirdAgeCategory;

	}

	addPerson(persons: InsurancePolicyPersonRequest[]){
		this.currentInsurancePolicy.persons = persons;	
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

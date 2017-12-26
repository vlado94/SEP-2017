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

	constructor() { }

	ngOnInit() {
		this.active_tab = 2;
	}

	changeTab(value: number){
		console.log("promeni tab")
		this.active_tab = value;
	}

	savePolicyRequest(insurancePolicyRequest: InsurancePolicyRequest){
		this.currentInsurancePolicy = insurancePolicyRequest;
	}


}

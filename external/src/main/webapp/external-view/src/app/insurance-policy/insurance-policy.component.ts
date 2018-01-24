import { Component, OnInit } from '@angular/core';
import { InsurancePolicyRequest } from './insurance-policy-request'
import { InsurancePolicyPersonRequest } from './insurance-policy-person-request';
import { InsurancePolicyHome } from './insurance-policy-home-car/policy-home';
import { InsurancePolicyCar } from './insurance-policy-home-car/policy-car';

import { InsurancePolicyService } from './insurance-policy.service'
import { InsurancePolicyCheckoutRequest } from './insurance-policy-checkout-request';


@Component({
	selector: 'app-insurance-policy',
	templateUrl: './insurance-policy.component.html',
	styleUrls: ['./insurance-policy.component.scss']
})
export class InsurancePolicyComponent implements OnInit {

	active_tab: number;
	currentInsurancePolicy: InsurancePolicyRequest;
	persons: InsurancePolicyPersonRequest[] = [];
	currentPerson: InsurancePolicyPersonRequest;
	age: Age = new Age(0, 0, 0);
	insurancePolicyHome: InsurancePolicyHome = null;
	insurancePolicyCar: InsurancePolicyCar = null;
	policyPrice = null;
	carInsurancePrice = null;
	homeInsurancePrice = null;
    checkout: string = null;



	constructor(private insurancePolicyService: InsurancePolicyService) { }

	ngOnInit() {
		this.active_tab = 1;
	}

	changeTab(value: number){
		console.log("promeni tab")
		this.active_tab = value;
		if (value == 4){
            this.currentInsurancePolicy.persons = this.persons;
            var checkoutRequest:InsurancePolicyCheckoutRequest = new InsurancePolicyCheckoutRequest(this.currentInsurancePolicy,this.insurancePolicyCar,this.insurancePolicyHome);
            this.insurancePolicyService.getCheckout(checkoutRequest).subscribe(result => {
                this.checkout = result;
            });

        }
	}

	savePolicyRequest(insurancePolicyRequest: InsurancePolicyRequest){
		this.currentInsurancePolicy = insurancePolicyRequest;
		this.age.firstCategory = insurancePolicyRequest.firstAgeCategory;
		this.age.secondCategory = insurancePolicyRequest.secondAgeCategory;
		this.age.thirdCategory = insurancePolicyRequest.thirdAgeCategory;

	}

	addPerson(persons: InsurancePolicyPersonRequest[]){
		this.currentInsurancePolicy.persons = persons;	
		this.persons = persons;
		console.log("osobe " + JSON.stringify(this.persons));
	}

	updatePerson(persons : InsurancePolicyPersonRequest[]){
		console.log("update person policy component");
		this.currentInsurancePolicy.persons = persons;
		console.log("persons stringify " + JSON.stringify(this.currentInsurancePolicy.persons));
	}

	insurancePolicyHomeChanged(value) {
		if (value && value.address != null)
			this.insurancePolicyHome = value;
		else {
			this.insurancePolicyHome = null;
		}
	}

	insurancePolicyCarChanged(value) {
		if (value && value.registrationNumber != null)
			this.insurancePolicyCar = value;
		else {
			this.insurancePolicyCar = null;
		}
	}

	calculateInsurancePolicyPrice(value) {
		if (value) {
			this.insurancePolicyService.calculateSuggestedPrice(value).subscribe(price => {
				this.policyPrice = price;
			})
		} else {
			this.policyPrice = null;
		}
	}

	calculateCarInsurancePrice(value) {
        if (value) {
            this.insurancePolicyService.calculateSuggestedPriceCar(value).subscribe(price => {
                this.carInsurancePrice = price;
            })
        } else {
            this.carInsurancePrice = null;
        }
    }

    calculateHomeInsurancePrice(value) {
        if (value != null) {
            this.insurancePolicyService.calculateSuggestedPriceHome(value).subscribe(price => {
                this.homeInsurancePrice = price;
            })
        } else {

            this.homeInsurancePrice = null;
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

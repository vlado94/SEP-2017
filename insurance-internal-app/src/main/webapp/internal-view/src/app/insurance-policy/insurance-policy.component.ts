import {Component, NgModule} from '@angular/core';
import {InsurancePolicyRequest} from './insurance-policy-request';

import {InsurancePolicyPersonRequest} from './insurance-policy-person/insurance-policy-person-form/insurance-policy-person-form.component';
import {InsurancePolicyCarRequest} from './insurance-policy-car-form/insurance-policy-car-request';
import {InsurancePolicyHomeRequest} from './insurance-policy-home-form/insurance-policy-home-request';
import {InsurancePolicyCarCalculatePriceRequest} from './insurance-policy-car-form/insurance-policy-car-calculate-price-request';
import {InsurancePolicyHomeCalculatePriceRequest} from './insurance-policy-home-form/insurance-policy-home-calculate-price-request';
import {InsurancePolicyService} from './insurance-policy.service';
@Component({
    selector: 'app-insurance-policy',
    templateUrl: './insurance-policy.component.html',
    styleUrls: ['./insurance-policy.component.css']
})


@NgModule({
    imports: [
    ],
    exports: [ // components that we want to make available
    ],
    declarations: [ // components for use in THIS module
    ],
    providers: [ // singleton services
        InsurancePolicyService
    ]
})
export class InsurancePolicyComponent {

    activeTab: string = '3';
    personsList: InsurancePolicyPersonRequest[] = [];
    currentInsurancePolicy: InsurancePolicyRequest;
    age: Age = new Age(0, 0, 0);
    insurancePolicyCar: InsurancePolicyCarRequest = null;
    insurancePolicyHome: InsurancePolicyHomeRequest = null;

    carInsurancePrice: string = null;
    homeInsurancePrice: string = null;


    constructor(private insurancePolicyService: InsurancePolicyService) { }

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

    calculateHomeInsurancePrice(value) {
        if (value != null) {

            console.log("222222222222222222222222222222222222");
            this.insurancePolicyService.calculateSuggestedPriceHome(value).subscribe(price => {
                this.homeInsurancePrice = price;
            })
        } else {

            this.homeInsurancePrice = null;
        }
    }

    calculateCarInsurancePrice(value) {
        if (value) {


            this.insurancePolicyService.calculateSuggestedPriceCar(value).subscribe(price => {
                this.carInsurancePrice = price;
            })
        } else {
            console.log("11111111111111111111111");
            this.carInsurancePrice = null;
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
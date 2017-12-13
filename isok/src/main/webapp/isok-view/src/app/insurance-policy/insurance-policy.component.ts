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
    currentInsurancePolicy:InsurancePolicyPersonRequest;
    
    nextTab(value: string) {
        this.activeTab = value;
    }
    
    onSubmit(insurancePolicyRequest:InsurancePolicyPersonRequest){
        this.currentInsurancePolicy = insurancePolicyRequest; 
    }
}

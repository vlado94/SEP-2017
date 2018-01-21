import {InsurancePolicyRequest} from './insurance-policy-request';
import {InsurancePolicyCarRequest} from './insurance-policy-car-form/insurance-policy-car-request';
import {InsurancePolicyHomeRequest} from './insurance-policy-home-form/insurance-policy-home-request';

export class InsurancePolicyCheckoutRequest {

    insurancePolicyRequest:  InsurancePolicyRequest;
    insurancePolicyCarRequest: InsurancePolicyCarRequest;
    insurancePolicyHomeRequest: InsurancePolicyHomeRequest; 

    constructor(insurancePolicyRequest: InsurancePolicyRequest,
        insurancePolicyCarRequest: InsurancePolicyCarRequest,
        insurancePolicyHomeRequest: InsurancePolicyHomeRequest) {
        this.insurancePolicyRequest = insurancePolicyRequest;
        this.insurancePolicyCarRequest = insurancePolicyCarRequest;
        this.insurancePolicyHomeRequest = insurancePolicyHomeRequest;
    } 

}
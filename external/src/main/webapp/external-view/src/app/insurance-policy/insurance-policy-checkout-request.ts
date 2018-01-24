import {InsurancePolicyRequest} from './insurance-policy-request';
import {InsurancePolicyCar} from './insurance-policy-home-car/policy-car';
import {InsurancePolicyHome} from './insurance-policy-home-car/policy-home';

export class InsurancePolicyCheckoutRequest {

    insurancePolicyRequest:  InsurancePolicyRequest;
    insurancePolicyCarRequest: InsurancePolicyCar;
    insurancePolicyHomeRequest: InsurancePolicyHome; 

    constructor(insurancePolicyRequest: InsurancePolicyRequest,
        insurancePolicyCarRequest: InsurancePolicyCar,
        insurancePolicyHomeRequest: InsurancePolicyHome) {
        this.insurancePolicyRequest = insurancePolicyRequest;
        this.insurancePolicyCarRequest = insurancePolicyCarRequest;
        this.insurancePolicyHomeRequest = insurancePolicyHomeRequest;
    } 

}
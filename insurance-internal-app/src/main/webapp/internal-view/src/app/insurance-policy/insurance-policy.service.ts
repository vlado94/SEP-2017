import { Injectable } from '@angular/core';
import { Http, Response } from "@angular/http";
import 'rxjs/add/operator/map'
import 'rxjs/add/operator/catch';
import { Subject } from "rxjs/Subject";
import { Observable } from "rxjs/Observable";
import { of } from 'rxjs/observable/of';
import {BehaviorSubject} from 'rxjs/BehaviorSubject';

import {InsurancePolicyRequest} from './insurance-policy-request';
import {InsurancePolicyCheckoutRequest} from './insurance-policy-checkout-request';
import {InsurancePolicyCalculatePriceRequest} from './insurance-policy-calculate-price-request';
import {InsurancePolicyCarCalculatePriceRequest} from './insurance-policy-car-form/insurance-policy-car-calculate-price-request';
import {InsurancePolicyHomeCalculatePriceRequest} from './insurance-policy-home-form/insurance-policy-home-calculate-price-request';
import { PinRequest } from './insurance-policy-pin/pin-request'
import {InsurancePolicyPersonRequest} from './insurance-policy-person/insurance-policy-person-form/insurance-policy-person-form.component';

import { environment } from '../../environments/environment';

@Injectable()
export class InsurancePolicyService {


    private apiUrl = `${environment.BACKEND_URL}/insurancePolicy`;

    // Using Angular DI we use the HTTP service 
    constructor(private http: Http) {

    }

    calculateSuggestedPrice(insurancePolicyCalculatePriceRequest:InsurancePolicyCalculatePriceRequest) {
        return this.http.post(this.apiUrl+"/calculateSuggestedPrice",insurancePolicyCalculatePriceRequest).map(res=>res.json());
    }
    calculateSuggestedPriceCar(insurancePolicyCarCalculatePriceRequest:InsurancePolicyCarCalculatePriceRequest) {
        return this.http.post(this.apiUrl+"/car/calculateSuggestedPrice",insurancePolicyCarCalculatePriceRequest).map(res=>res.json());
    }

    calculateSuggestedPriceHome(insurancePolicyHomeCalculatePriceRequest:InsurancePolicyHomeCalculatePriceRequest) {
        return this.http.post(this.apiUrl+"/home/calculateSuggestedPrice",insurancePolicyHomeCalculatePriceRequest).map(res=>res.json());
    }
    
    
    getCheckout(checkoutRequest:InsurancePolicyCheckoutRequest ){
        return this.http.post(this.apiUrl+"/checkout",checkoutRequest)
        .map(res=>res.json());    
    }
    
    create(insurancePolicy: InsurancePolicyRequest) {
        return this.http.post(this.apiUrl, insurancePolicy)
            .map(res => res.json());
    }

    
    save(insurancePolicyCheckoutResponse){
        return this.http.post(this.apiUrl+"/save",insurancePolicyCheckoutResponse).map(res=>res.json());
    }
    setPaid(id){

        return this.http.get(this.apiUrl+"/paid/"+id,).map(res=>res.json());
    }
    
    pin(pinRequest: PinRequest){
        return this.http.post('http://localhost:8086/activateJC/checkPin/', pinRequest)
            .map(res=>res.json());
    }

    bankMembers(){
        return this.http.get(this.apiUrl+"/bankMember").map(res=>res.json());
    }

}
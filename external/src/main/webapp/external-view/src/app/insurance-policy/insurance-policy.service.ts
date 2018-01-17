import { Injectable } from '@angular/core';
import { Http, Response } from "@angular/http";
import 'rxjs/add/operator/map'
import 'rxjs/add/operator/catch';
import { InsurancePolicyCalculatePriceRequest } from './insurance-policy-calculate-price'
import { InsurancePolicyCarCalculatePriceRequest } from './insurance-policy-home-car/policy-car-calculate-price-request';
import { InsurancePolicyHomeCalculatePriceRequest } from './insurance-policy-home-car/policy-home-calculate-price-request';

import { environment } from '../../environments/environment';


@Injectable()
export class InsurancePolicyService {

	private apiUrl = `${environment.BACKEND_URL}`;

	constructor(private http: Http) { }

	calculateSuggestedPrice(insurancePolicyCalculatePriceRequest:InsurancePolicyCalculatePriceRequest) {
		return this.http.post(this.apiUrl+"/calculateSuggestedPrice",insurancePolicyCalculatePriceRequest).map(res=>res.json());
	}

	calculateSuggestedPriceCar(insurancePolicyCarCalculatePriceRequest:InsurancePolicyCarCalculatePriceRequest) {
		return this.http.post(this.apiUrl+"/calculateSuggestedPrice/car",insurancePolicyCarCalculatePriceRequest).map(res=>res.json());
	}

	calculateSuggestedPriceHome(insurancePolicyHomeCalculatePriceRequest:InsurancePolicyHomeCalculatePriceRequest) {
        return this.http.post(this.apiUrl+"/calculateSuggestedPrice/home",insurancePolicyHomeCalculatePriceRequest).map(res=>res.json());
    }
}

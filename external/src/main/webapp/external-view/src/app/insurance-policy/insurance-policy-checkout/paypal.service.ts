import { Injectable } from '@angular/core';
import { Http, Response } from "@angular/http";
import 'rxjs/add/operator/map'
import 'rxjs/add/operator/catch';
import { InsurancePolicyRequest } from '../insurance-policy-request'
import { Observable } from 'rxjs/Observable';

import { environment } from '../../../environments/environment';

@Injectable()
export class PaypalService {

	private apiUrl = `${environment.BACKEND_URL}`;

	constructor(private http: Http) { }

	paypal(currentInsurancePolicy:InsurancePolicyRequest) : Observable<string> {
		return this.http.post(this.apiUrl+"/paypal",currentInsurancePolicy).map(res=>res.text());
	}

}

import { Injectable } from '@angular/core';
import { Http, Response } from "@angular/http";
import { environment } from '../environments/environment';
import { PaymentRequest } from './payment-request';
import 'rxjs/add/operator/map'

@Injectable()
export class AppService {

	private apiUrl = `${environment.BACKEND_URL}`;
	
	constructor (
    private http: Http
  	) {}

	postPayment(paymentRequest:PaymentRequest) {
		//console.log("Inservice");
		return this.http.post(this.apiUrl+"/pay", paymentRequest).map(res => res.text())
		//console.log(res);
	}

}

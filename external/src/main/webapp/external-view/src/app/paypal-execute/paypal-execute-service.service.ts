import { Injectable } from '@angular/core';
import { Http, Response } from "@angular/http";
import 'rxjs/add/operator/map'
import 'rxjs/add/operator/catch';
import { Observable } from 'rxjs/Observable';

import { environment } from '../../environments/environment';

@Injectable()
export class PaypalExecuteServiceService {

	private apiUrl = `${environment.BACKEND_URL}`;

	constructor(private http: Http) { }

	execute(payerID:string, paymentId:string) : Observable<boolean> {
		return this.http.get(this.apiUrl+"/paypal/execute", { params: {
        	paymentId: paymentId,
        	PayerID: payerID
     	}}).
		map(res=>res.json());
	}

}

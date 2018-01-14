import { Injectable } from '@angular/core';
import { Http, Response } from "@angular/http";
import 'rxjs/add/operator/map'
import 'rxjs/add/operator/catch';
import { Subject } from "rxjs/Subject";

import { environment } from '../../environments/environment';


@Injectable()
export class ResolverServiceService {

	private apiUrl = `${environment.BACKEND_URL}/resolve`;


	constructor(private http: Http) { }


    getRoles() {
        return this.http.get(this.apiUrl)
            .map(res => res.json());
    }

    getRolePriceManagement() {
    	 return this.http.get(this.apiUrl + "/price_management")
            .map(res => res.json());
    }


}

import { Injectable } from '@angular/core';
import { Http, Response } from "@angular/http";
import 'rxjs/add/operator/map'
import 'rxjs/add/operator/catch';
import { Subject } from "rxjs/Subject";

import { environment } from '../../environments/environment';

@Injectable()
export class ResolverServiceService {

	private apiUrl = 'http://localhost:8082/resolve'

	constructor(private http: Http) { }

	getRole() {
        return this.http.get(this.apiUrl)
            .map(res => res.json());
    }

}

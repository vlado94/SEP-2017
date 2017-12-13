import { Injectable } from '@angular/core';
import { Http, Response } from "@angular/http";
import 'rxjs/add/operator/map'
import 'rxjs/add/operator/catch';
import { Subject } from "rxjs/Subject";
import { Observable } from "rxjs/Observable";
import { of } from 'rxjs/observable/of';
import {BehaviorSubject} from 'rxjs/BehaviorSubject';

import {InsurancePolicyRequest} from './insurance-policy-form/insurance-policy-form.component';

import {InsurancePolicyPersonRequest} from './insurance-policy-person/insurance-policy-person-form/insurance-policy-person-form.component';

import { environment } from '../../environments/environment';

@Injectable()
export class InsurancePolicyService {


    private apiUrl = `${environment.BACKEND_URL}/insurancePolicy`;

    // Using Angular DI we use the HTTP service
    constructor(private http: Http) {

    }



    create(insurancePolicy: InsurancePolicyRequest) {

        return this.http.post(this.apiUrl, insurancePolicy)
            .map(res => res.json());
    }





}
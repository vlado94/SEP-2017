import { Injectable } from '@angular/core';
import { Http, Response } from "@angular/http";
import 'rxjs/add/operator/map'
import 'rxjs/add/operator/catch';
import { Subject } from "rxjs/Subject";

import { Factor } from './factor'
import { environment } from '../../environments/environment';

@Injectable()
export class FactorService {

    private apiUrl = `${environment.BACKEND_URL}/factor`;

    constructor(private http: Http) { }

    findByCategory(categoryId) {
        return this.http.get(this.apiUrl + '/category/' + categoryId)
            .map(res => res.json());
    }
}

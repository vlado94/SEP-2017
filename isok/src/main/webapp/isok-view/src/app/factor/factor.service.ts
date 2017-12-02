import { Injectable } from '@angular/core';
import { Http, Response } from "@angular/http";
import 'rxjs/add/operator/map'
import 'rxjs/add/operator/catch';
import { Subject } from "rxjs/Subject";

import { Factor } from './factor'

@Injectable()
export class FactorService {

    private apiUrl = 'http://localhost:8080/factor';

    constructor(private http: Http) { }

    findAll() {
        return this.http.get(this.apiUrl)
            .map(res => res.json());
    }

    save(factor) {
        return this.http.post(this.apiUrl, factor)
            .map(res => res.json());

    }

    deleteById(id) {
        return this.http.delete(this.apiUrl + '/' + id)
            .map(res => res.json());
    }

    get(id: number) {
        return this.http.get(this.apiUrl + '/' + id)
            .map((res: Response) => res.json());
    }

    update(factor) {
        return this.http.put(this.apiUrl, factor)
            .map((res: Response) => res.json());
    }

    findByCategory(categoryId) {
        return this.http.get(this.apiUrl + '/category/' + categoryId)
            .map(res => res.json());
    }
}

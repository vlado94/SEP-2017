import { Injectable } from '@angular/core';
import { Http, Response } from "@angular/http";
import 'rxjs/add/operator/map'
import 'rxjs/add/operator/catch';
import { Subject } from "rxjs/Subject";

import { CategoryFactor } from './category-factor'

@Injectable()
export class CategoryFactorService {

    private apiUrl = 'http://localhost:8080/categoryFactor';

    constructor(private http: Http) { }

    findAll() {
        return this.http.get(this.apiUrl)
            .map(res => res.json());
    }

    findFactorsByID(id) {
        return this.http.get(this.apiUrl+'/findFactorsByID/' + id)
            .map(res => res.json());
    }

    save(categoryFactor) {
        return this.http.post(this.apiUrl, categoryFactor)
            .map(res => res.json())
    }

    deleteById(id){
        return this.http.delete(this.apiUrl + '/' + id)
          .map(res => res.json());
    }

    get(id) {
        return this.http.get(this.apiUrl + '/' + id)
            .map((res: Response) => res.json());
    }

    update(categoryFactor)  {
        return this.http.put(this.apiUrl, categoryFactor)
            .map((res: Response) => res.json());
    }


}

import { Injectable } from '@angular/core';
import { Http, Response } from "@angular/http";
import 'rxjs/add/operator/map'
import 'rxjs/add/operator/catch';
import { Observable } from "rxjs/Observable";
import { CategoryFactor } from './category-factor'


@Injectable()
export class CategoryFactorService {

    private apiUrl = 'http://localhost:8080/categoryFactor';

    constructor(private http: Http) { }

    findAll() {
        return this.http.get(this.apiUrl)
            .map((res: Response) => res.json());
    }

    saveCategory(categoryFactor): Observable<CategoryFactor> {
        return this.http.post(this.apiUrl, categoryFactor).map((res: Response) => res.json());
    }

    deleteById(id: number): Observable<boolean> {
        return this.http.delete(this.apiUrl + '/' + id)
            .map((res: Response) => res.json())
            .catch((error: any) => Observable.throw(error.json().error || 'Server error'));
    }

    findById(id: number): Observable<CategoryFactor> {
        return this.http.get(this.apiUrl + '/' + id)
            .map((res: Response) => res.json());
    }

    updateCategoryFactor(categoryFactor): Observable<CategoryFactor>  {
        return this.http.put(this.apiUrl, categoryFactor)
            .map((res: Response) => res.json());
    }


}

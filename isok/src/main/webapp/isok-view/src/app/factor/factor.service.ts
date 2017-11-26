import { Injectable } from '@angular/core';
import { Http, Response } from "@angular/http";
import 'rxjs/add/operator/map'
import 'rxjs/add/operator/catch';
import { Subject } from "rxjs/Subject";
import { Observable } from "rxjs/Observable";
import { of } from 'rxjs/observable/of';
 
import { Factor } from './factor'

    
@Injectable()
export class FactorService {

	private apiUrl = 'http://localhost:8080/factor';

    public factors: Observable<Factor[]>;
    public newFactor = new Subject<any>();

    constructor(private http: Http) { }

    findAll() {
        return this.http.get(this.apiUrl)
            .map(res => res.json());
    }

    save(factor) {
        //this.newFactor.next(factor);
        this.http.post(this.apiUrl, factor).map(res => res.json())
            .subscribe(data => this.newFactor.next(data));
    }

    deleteById(id){
        return this.http.delete(this.apiUrl + '/' + id)
          .map(res => res.json());
    }

    get(id: number) {
        return this.http.get(this.apiUrl + '/' + id)
            .map((res: Response) => res.json());
    }

    update(factor)  {
        this.http.put(this.apiUrl, factor)
            .map((res: Response) => res.json())
            .subscribe(data => this.newFactor.next(data));
    }

    findIndexToUpdate(newItem) { 
        return newItem.id === this;
    }
}

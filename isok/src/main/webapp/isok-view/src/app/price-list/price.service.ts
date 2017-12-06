import { Injectable } from '@angular/core';
import { Http, Response } from "@angular/http";

@Injectable()
export class PriceService {
	private apiUrl = 'http://localhost:8080/priceList';

    constructor(private http: Http) { }

    findLast() {
        return this.http.get(this.apiUrl+'/findLast')
            .map(res => res.json());
    }

    addPriceList(values) {
        return this.http.post(this.apiUrl,values)
                .map(res => res.json());

    }
}

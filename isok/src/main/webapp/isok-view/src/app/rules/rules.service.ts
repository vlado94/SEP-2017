import { Injectable } from '@angular/core';
import { Http } from '@angular/http';

@Injectable()
export class RulesService {

    private apiUrl = 'http://localhost:8080/rules';

    constructor(private http: Http) { }

    getRules() {
        return this.http.get(this.apiUrl + '/getRules')
            .map(res => res.json());
    }
}

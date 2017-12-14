import { RulesHelper } from './rules.rulesHelper';
import { Injectable } from '@angular/core';
import { Http, RequestOptions, Headers } from '@angular/http';

@Injectable()
export class RulesService {

    private apiUrl = 'http://localhost:8080/rules';

    constructor(private http: Http) { }

    getRules() {
        return this.http.get(this.apiUrl + '/getRules')
            .map(res => res.text());
    }

    changeRules(s: RulesHelper) {
        // const cpHeaders = new Headers({ 'Content-Type': ' text/plain' });
        // const options = new RequestOptions({ headers : cpHeaders});
        return this.http.post(this.apiUrl + '/changeRules', s).map(res => res.json());
    }

}

import { Injectable } from '@angular/core';
import { Http, Response } from "@angular/http";
import 'rxjs/add/operator/map'
import 'rxjs/add/operator/catch';
import { Subject } from "rxjs/Subject";
import { Observable } from "rxjs/Observable";
import { of } from 'rxjs/observable/of';
import {BehaviorSubject} from 'rxjs/BehaviorSubject';
import {InsurancePolicyPersonRequest} from './insurance-policy-person/insurance-policy-person-form.component';

@Injectable()
export class InsurancePolicyService {

    private _todos: BehaviorSubject<InsurancePolicyPersonRequest[]>;
    private dataStore: {  // This is where we will store our data in memory
        todos: InsurancePolicyPersonRequest[]
    };

    // Using Angular DI we use the HTTP service
    constructor(private http: Http) {
        this.dataStore = { todos: [] };
        this._todos = <BehaviorSubject<InsurancePolicyPersonRequest[]>>new BehaviorSubject([]);
    }

    get todos() {
        return this._todos.asObservable();
    }

    add(person: InsurancePolicyPersonRequest) {
        this.dataStore.todos.push(person);
        this._todos.next(this.dataStore.todos);
    }
    /*constructor(private http: Http) { }


    private _list: InsurancePolicyPersonRequest[] = [];
    private _observableList: BehaviorSubject<InsurancePolicyPersonRequest[]> = new BehaviorSubject([]);

    get observableList(): Observable<InsurancePolicyPersonRequest[]> { return this._observableList.asObservable() }

    add(person: InsurancePolicyPersonRequest) {
        this._list.push(person);
        this._observableList.next(this._list);
        console.log(person.firstName);
    }*/

}
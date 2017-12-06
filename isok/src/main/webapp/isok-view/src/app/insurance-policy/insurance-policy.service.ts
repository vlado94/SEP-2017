import { Injectable } from '@angular/core';
import { Http, Response } from "@angular/http";
import 'rxjs/add/operator/map'
import 'rxjs/add/operator/catch';
import { Subject } from "rxjs/Subject";
import { Observable } from "rxjs/Observable";
import { of } from 'rxjs/observable/of';
import {BehaviorSubject} from 'rxjs/BehaviorSubject';

import {InsurancePolicyRequest} from './insurance-policy-form/insurance-policy-form.component';

import {InsurancePolicyPersonRequest} from './insurance-policy-person/insurance-policy-person-form.component';

import { environment } from '../../environments/environment';

@Injectable()
export class InsurancePolicyService {


    private apiUrl = `${environment.BACKEND_URL}/insurancePolicy`;

    private _todos: BehaviorSubject<InsurancePolicyPersonRequest[]>;
    private _personForm = new BehaviorSubject<boolean>(false);
    private _contractorAdded = new BehaviorSubject<boolean>(false);

    private _currentPerson = new Subject<InsurancePolicyPersonRequest>();;
    private dataStore: {
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

    get personForm() {
        return this._personForm.asObservable();
    }

    get currentPerson() {
        return this._currentPerson.asObservable();
    }

    get contractorAdded() {
        return this._contractorAdded.asObservable();
    }

    create(insurancePolicy: InsurancePolicyRequest) {

        return this.http.post(this.apiUrl, insurancePolicy)
            .map(res => res.json());
    }

    add(person: InsurancePolicyPersonRequest) {
        console.log("123");
        this.dataStore.todos.push(person);
        this._todos.next(this.dataStore.todos);
        this.checkIfContractorExists();
    }

    checkIfContractorExists() {
        let x = false;
        for (let person of this.dataStore.todos) {
            if (person.contractor) {
                this._contractorAdded.next(true);
                x = true;
            }
        }
        if (!x)
            this._contractorAdded.next(false);

    }

    deletePerson(person: InsurancePolicyPersonRequest) {
        const index: number = this.dataStore.todos.indexOf(person);
        if (index !== -1) {
            this.dataStore.todos.splice(index, 1);
            this._todos.next(this.dataStore.todos);
        }
        this.checkIfContractorExists();
    }

    changePersonFormVisibility(value: boolean) {
        this._personForm.next(value);
    }

    setCurrentPerson(person: InsurancePolicyPersonRequest) {
        this._currentPerson.next(person);
        this._personForm.next(true);
    }
}
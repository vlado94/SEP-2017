import {Component} from '@angular/core';
import {InsurancePolicyRequest} from './insurance-policy-form/insurance-policy-form.component';

import {InsurancePolicyPersonRequest} from './insurance-policy-person/insurance-policy-person-form.component';


@Component({
    selector: 'app-insurance-policy',
    templateUrl: './insurance-policy.component.html',
    styleUrls: ['./insurance-policy.component.css']
})

export class InsurancePolicyComponent {

    lista: string[] = ['123', '123', '456'];
    personsList: InsurancePolicyPersonRequest[] = [];
    currentPerson: InsurancePolicyPersonRequest = null;
    formPerson: boolean = false;

    onSelectForUpdate(person: InsurancePolicyPersonRequest) {
        this.currentPerson = person;
        this.formPerson = true;
    }

    toggleFormPerson(value: boolean) {
        this.formPerson = value;
    }
}

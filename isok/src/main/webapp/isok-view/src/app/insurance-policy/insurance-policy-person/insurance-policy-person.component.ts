import {Component, Output, EventEmitter} from '@angular/core';

import {InsurancePolicyPersonRequest} from './insurance-policy-person-form/insurance-policy-person-form.component';

@Component({
    selector: 'app-insurance-policy-person',
    templateUrl: './insurance-policy-person.component.html',
    styleUrls: ['./insurance-policy-person.component.css']
})

export class InsurancePolicyPersonComponent {

    currentPerson: InsurancePolicyPersonRequest;
    persons: InsurancePolicyPersonRequest[] = [];
    @Output() nextTab = new EventEmitter<string>();

    changeTab(value: string) {
        this.nextTab.emit(value);
    }

    onSubmit(value: InsurancePolicyPersonRequest) {
        if (this.currentPerson) {
            let index = this.persons.indexOf(this.currentPerson);
            this.persons.splice(index, 1, value);
            this.currentPerson = null;
            return;
        }
        this.persons.push(value);
    }

    setForUpdate(value: InsurancePolicyPersonRequest) {
        this.currentPerson = value;
    }

    delete(value: InsurancePolicyPersonRequest) {
        let index = this.persons.indexOf(value);
        if (index != -1) {
            this.persons.splice(index, 1);
        }
        if (this.currentPerson == value)
            this.currentPerson = null;

        resetCurrent(value: InsurancePolicyPersonRequest) {
            this.currentPerson = value;
        }

    }
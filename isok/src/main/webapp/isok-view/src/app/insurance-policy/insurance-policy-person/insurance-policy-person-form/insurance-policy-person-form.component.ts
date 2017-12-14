import {Component, OnInit, Input, Output, EventEmitter} from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {Router} from '@angular/router';
import { NgModule } from '@angular/core';

import { FactorService } from "../../../factor/factor.service";
import { Factor } from '../../../factor/factor';

import {InsurancePolicyService} from '../../insurance-policy.service';
import {Age} from '../../insurance-policy.component';

//import {InsurancePolicyPersonRequest} from './insurance-policy-person-request';

@Component({
    selector: 'app-insurance-policy-person-form',
    templateUrl: './insurance-policy-person-form.component.html',
    styleUrls: ['./insurance-policy-person-form.component.css']

})
@NgModule({
    imports: [
    ],
    exports: [ // components that we want to make available
    ],
    declarations: [ // components for use in THIS module
    ],
    providers: [ // singleton services
        InsurancePolicyService,
        FactorService
    ]
})
export class InsurancePolicyPersonFormComponent {

    @Input() persons;
    insurancePolicyPerson: FormGroup;
    public submitted: boolean;

    currentPerson: InsurancePolicyPersonRequest = null;
    contractorAdded: boolean = false;
    @Output() onFormSubmit = new EventEmitter<InsurancePolicyPersonRequest>();
    @Output() resetCurrent = new EventEmitter<InsurancePolicyPersonRequest>();

    constructor(private insurancePolicyService: InsurancePolicyService, private factorService: FactorService) {

        this.insurancePolicyPerson = new FormGroup({
            firstName: new FormControl('', [
                Validators.required,
                Validators.minLength(3)
            ]),
            lastName: new FormControl('', [
                Validators.required,
                Validators.minLength(3)
            ]),
            jmbg: new FormControl('', [
                Validators.required,
                Validators.minLength(13),
                Validators.maxLength(13)
            ]),
            passportNumber: new FormControl(''),

            address: new FormControl('', [
                Validators.required
            ]),
            phone: new FormControl('', [
                Validators.required
            ]),
            contractor: new FormControl('false', [
            ]),
            email: new FormControl('', [
            ]),
        })
    }

    ngOnInit() {
        this.insurancePolicyPerson.reset();
        this.contractorExists;
    }

    @Input()
    set current(person: InsurancePolicyPersonRequest) {
        this.currentPerson = person;
        if (person) {
            this.insurancePolicyPerson.setValue({
                firstName: this.currentPerson.firstName,
                lastName: this.currentPerson.lastName,
                jmbg: this.currentPerson.jmbg,
                passportNumber: this.currentPerson.passportNumber,
                address: this.currentPerson.address,
                phone: this.currentPerson.phone,
                contractor: this.currentPerson.contractor.toString(),
                email: this.currentPerson.email
            })
        }

    }
    onSubmit({value}: { value: InsurancePolicyPersonRequest }) {
        let newPerson: InsurancePolicyPersonRequest = new InsurancePolicyPersonRequest(value.firstName, value.lastName, value.jmbg, value.passportNumber,
            value.address, value.phone, value.contractor == 'true', value.email);
        this.onFormSubmit.emit(newPerson);
        this.insurancePolicyPerson.reset();
    }

    jmbgExists() {
        let result = false;
        let jmbg = this.insurancePolicyPerson.get('jmbg').value
        if (jmbg != '') {
            for (let person of this.persons) {
                if (person.jmbg === jmbg) {
                    if (this.currentPerson != null) {
                        if (jmbg != this.currentPerson.jmbg) {
                            result = true;
                        }
                    } else {
                        result = true;
                    }
                }
            }
        }
        return result;
    }

    contractorExists() {
        console.log("Provera da li su uneti podaci za ugovaraca polise.")
        let result = false;
        for (let person of this.persons) {
            if (!!person.contractor) {
                result = true;
            }
        }
        if (result)
            console.log("Podaci za ugovaraca polise su uneti.")
        else
            console.log("Podaci za ugovaraca polise nisu uneti.")

        return result;
    }

    reset() {
        this.insurancePolicyPerson.reset();
        this.resetCurrent.emit(null);
    }
}

export class InsurancePolicyPersonRequest {
    firstName: string;
    lastName: string;
    jmbg: string;
    passportNumber: string;
    address: string;
    phone: string;
    contractor: boolean;
    email: string;
    constructor(firstName: string,
        lastName: string,
        jmbg: string,
        passportNumber: string,
        address: string,
        phone: string,
        contractor: boolean,
        email: string) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.jmbg = jmbg;
        this.passportNumber = passportNumber;
        this.address = address;
        this.phone = phone;
        this.contractor = contractor;
        this.email = email;
    }
}
import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { NgModule } from '@angular/core';

import { FactorService } from "../../../factor/factor.service";
import { Factor } from '../../../factor/factor';

import { InsurancePolicyService } from '../../insurance-policy.service';
import { Age } from '../../insurance-policy.component';

//import {InsurancePolicyPersonRequest} from './insurance-policy-person-request';

@Component( {
    selector: 'app-insurance-policy-person-form',
    templateUrl: './insurance-policy-person-form.component.html',
    styleUrls: ['./insurance-policy-person-form.component.css']

} )
@NgModule( {
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
} )
export class InsurancePolicyPersonFormComponent {

    @Input() persons;
    @Input() contractorExists;
    insurancePolicyPerson: FormGroup;
    public submitted: boolean;

    currentPerson: InsurancePolicyPersonRequest = null;
    contractorAdded: boolean = false;
    @Output() onFormSubmit = new EventEmitter<InsurancePolicyPersonRequest>();
    @Output() resetCurrent = new EventEmitter<InsurancePolicyPersonRequest>();
    jmbgExists: boolean = false;
    constructor( private insurancePolicyService: InsurancePolicyService, private factorService: FactorService ) {

        this.insurancePolicyPerson = new FormGroup( {
            firstName: new FormControl( '', [
                Validators.required,
                Validators.minLength( 3 )
            ] ),
            lastName: new FormControl( '', [
                Validators.required,
                Validators.minLength( 3 )
            ] ),
            jmbg: new FormControl( '', [
                Validators.required,
                Validators.minLength( 13 ),
                Validators.maxLength( 13 )
            ] ),
            passportNumber: new FormControl( '', [Validators.required] ),

            address: new FormControl( '', [
                Validators.required
            ] ),
            phone: new FormControl( '', [
                Validators.required
            ] ),
            contractor: new FormControl( 'false', [
            ] ),
            email: new FormControl( '', [
            ] ),
        } )
    }

    ngOnInit() {
        this.insurancePolicyPerson.reset();
        this.contractorExists;



        this.insurancePolicyPerson.controls['jmbg'].valueChanges.subscribe(
            selectedValue => {
                let result = false;
                let jmbg = selectedValue;
                if ( jmbg != '' ) {
                    for ( let person of this.persons ) {
                        if ( person.personNo === jmbg ) {
                            if ( this.currentPerson != null ) {
                                if ( jmbg != this.currentPerson.personNo ) {
                                    result = true;
                                }
                            } else {
                                result = true;
                            }
                        }
                    }
                }
                this.jmbgExists = result;
            }
        );

        this.insurancePolicyPerson.controls['contractor'].valueChanges.subscribe(
            selectedValue => {
                if ( selectedValue == 'false' || selectedValue == null) {
                    this.insurancePolicyPerson.controls['email'].setValue( '' );
                    this.insurancePolicyPerson.controls['email'].setValidators(null);
                } 
                else {
                    this.insurancePolicyPerson.controls['email'].setValidators( [Validators.required, Validators.email] );
                }
                this.insurancePolicyPerson.controls['email'].updateValueAndValidity();
            }
        );
    }

    @Input()
    set current( person: InsurancePolicyPersonRequest ) {
        this.currentPerson = person;
        if ( person ) {
            this.insurancePolicyPerson.setValue( {
                firstName: this.currentPerson.firstName,
                lastName: this.currentPerson.lastName,
                jmbg: this.currentPerson.personNo,
                passportNumber: this.currentPerson.passportNo,
                address: this.currentPerson.address,
                phone: this.currentPerson.phone,
                contractor: this.currentPerson.contractor.toString(),
                email: this.currentPerson.email
            } )
        }

    }
    onSubmit( { value }: { value } ) {
        let newPerson: InsurancePolicyPersonRequest = new InsurancePolicyPersonRequest( value.firstName, value.lastName, value.jmbg, value.passportNumber,
            value.address, value.phone, value.contractor === 'true', value.email );
        this.onFormSubmit.emit( newPerson );

        /*this.insurancePolicyPerson.controls['email'].setValue( '' );
        this.insurancePolicyPerson.controls['email'].setValidators(null);  
        this.insurancePolicyPerson.controls['email'].updateValueAndValidity();*/

        this.insurancePolicyPerson.reset();
    }

    reset() {
        this.insurancePolicyPerson.reset();
        this.resetCurrent.emit( null );
    }
}

export class InsurancePolicyPersonRequest {
    firstName: string;
    lastName: string;
    personNo: string;
    passportNo: string;
    address: string;
    phone: string;
    contractor: boolean;
    email: string;
    constructor( firstName: string,
        lastName: string,
        personNo: string,
        passportNo: string,
        address: string,
        phone: string,
        contractor: boolean,
        email: string ) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.personNo = personNo;
        this.passportNo = passportNo;
        this.address = address;
        this.phone = phone;
        this.contractor = contractor;
        this.email = email;
    }
}
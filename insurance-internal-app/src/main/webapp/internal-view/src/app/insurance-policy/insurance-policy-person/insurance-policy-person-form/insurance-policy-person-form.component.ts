import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { NgModule } from '@angular/core';

import { FactorService } from "../../../factor/factor.service";
import { Factor } from '../../../factor/factor';

import { InsurancePolicyService } from '../../insurance-policy.service';
import { Age } from '../../insurance-policy.component';

//import {InsurancePolicyPersonRequest} from './insurance-policy-person-request';


function personNoValidator( personNo: FormControl ) {
    let result: boolean = true;
    let personNumber = personNo.value;
    if ( personNumber != null && personNumber.length == 13) {
        let dd = +personNumber.substring( 0, 2 );
        let mm = +personNumber.substring( 2, 4 );
        let ggg = +personNumber.substring( 4, 7 );
        let rr = +personNumber.substring( 7, 9 );
        let bbb = +personNumber.substring( 9, 12 );
        let k = +personNumber.substring( 12 );
        let controlNum = 11 - ( ( 7 * ( +personNumber[0] + ( +personNumber[6] ) ) + 6 * ( +personNumber[1] + ( +personNumber[7] ) ) + 5 * ( +personNumber[2] + ( +personNumber[8] ) ) + 4 * ( +personNumber[3] + ( +personNumber[9] ) ) + 3 * ( +personNumber[4] + ( +personNumber[10] ) ) + 2 * ( +personNumber[5] + ( +personNumber[11] ) ) ) % 11 );
        
        
        
        if(mm == 1 || mm == 3 || mm == 5 || mm == 7 || mm == 8 || mm == 10 || mm == 12){
            if(!(dd >0 && dd <= 31)){
                result = result && false;
            }
        }else if(mm == 4 || mm == 6 || mm == 9 || mm == 11){
            if(!(dd >0 && dd <= 30)){
                result = result && false;
            }
        }/*else if(mm == 2 && (ggg % 4==0 && ggg % 100 != 0)){
            if(dd >0 && dd <= 29){
                result = result && true;
            }else{
                result = result && false;
            }
        }*/else if(mm == 2){
            if(!(dd >0 && dd <= 28)){
                result = result && false;
            }            
        }
        
        if(!(mm >0 && mm <= 12)){
            result = result && false;
        }
        
        if(!(ggg >= 0 &&  ggg<=999)){
            result = result && false;
        }
        
        if(!(rr >= 0 && rr<=99)){
            result = result && false;
        }
        if(!(bbb >=0 && bbb <= 999)){
            result = result && false;
        }
        
        if(!(k == controlNum)){
            result = result && false;
        }
    }
    
    if ( !result )
        return {
        personNoValidator: {
                valid: false
            }
        }
    else
        return null;
}



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
                Validators.maxLength( 13 )/*, personNoValidator*/
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
                if ( selectedValue == 'false' || selectedValue == null ) {
                    this.insurancePolicyPerson.controls['email'].setValue( '' );
                    this.insurancePolicyPerson.controls['email'].setValidators( null );
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
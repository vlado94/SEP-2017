import { Component, OnInit, Input } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { InsurancePolicyService } from '../insurance-policy.service'
import { PinRequest } from './pin-request';
import { Observable } from 'rxjs/Rx';

@Component( {
    selector: 'app-insurance-policy-pin',
    templateUrl: './insurance-policy-pin.component.html',
    styleUrls: ['./insurance-policy-pin.component.css']
} )
export class InsurancePolicyPinComponent implements OnInit {

    pinForm: FormGroup;
    pin: boolean;
    requestPin: PinRequest;
    bankMembers;
    @Input() totalPrice;
    @Input() policyId;
    enteredCard = false;
    processing = false;
    feedback = '';
    constructor( private insurancePolicyService: InsurancePolicyService ) { }

    ngOnInit() {
        this.pinForm = new FormGroup( {
            cardHolder: new FormControl( '', { validators: [Validators.required] } ),
            pin: new FormControl( '', {
                validators: [Validators.required, Validators.pattern( "[0-9]*" ),
                Validators.maxLength( 4 ), Validators.minLength( 4 )]
            } ),
        } )

        this.insurancePolicyService.bankMembers().subscribe(
            data => {
                this.bankMembers = data;
            }
        );

        this.pinForm.controls['cardHolder'].valueChanges.subscribe( value => {
            if ( value != '' ) {
                this.enteredCard = true;
            }
        } )
    }

    setPaid( id ) {

    }
    redirectToHome(){
        window.location.href = 'http://localhost:4500/insurancePolicy';
    }
    
    
    
    submitPin() {
        this.processing = true;
        this.feedback = '';
        this.requestPin = this.pinForm.value;
        this.requestPin.totalPrice = this.totalPrice;
        this.requestPin.policyId = this.policyId;
        console.log( "pin kod " + this.requestPin.pin );
        this.insurancePolicyService.pin(this.requestPin)
            .subscribe(response => {
                let result = response;
                result = 'Done';
                if (  result == 'Done' ) {
                    console.log( response );
                    this.insurancePolicyService.setPaid(this.policyId)
                    .subscribe(message => {
                        console.log(message);
    
                    });
                    this.feedback = 'done';
                } else if ( result == 'Error' ) {
                    this.feedback = 'error';
                } else if ( result == 'Wrong pin' ) {
                    this.feedback = 'wrong';
                } else if ( result == 'Wrong pin,card is blocked' ) {
                    this.feedback = 'block';
                }

                this.processing = false;
               
        });

       // this.insurancePolicyService.pinTest()
           
    }

}

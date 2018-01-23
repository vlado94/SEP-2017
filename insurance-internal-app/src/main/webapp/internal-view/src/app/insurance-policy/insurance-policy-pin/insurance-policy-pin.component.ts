import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import { InsurancePolicyService } from '../insurance-policy.service'
import { PinRequest } from './pin-request';

@Component({
  selector: 'app-insurance-policy-pin',
  templateUrl: './insurance-policy-pin.component.html',
  styleUrls: ['./insurance-policy-pin.component.css']
})
export class InsurancePolicyPinComponent implements OnInit {

	pinForm: FormGroup;
	pin : boolean;
	requestPin : PinRequest;


 	constructor(private insurancePolicyService: InsurancePolicyService) { }

  	ngOnInit() {
  		this.pinForm = new FormGroup({
            cardHolder: new FormControl('', {validators:[Validators.required]}),
            pin: new FormControl('',{validators: [Validators.required,Validators.pattern("[0-9]*"), 
            	Validators.maxLength(4), Validators.minLength(4)]}),
  		})
  	}

  	submitPin(){
  		this.requestPin = this.pinForm.value;
  		console.log("pin kod " + this.requestPin.pin);
  		this.insurancePolicyService.pin(this.requestPin)
  			.subscribe(pin => {
                this.pin = pin;
                console.log("vracen pin " + this.pin);
            });
  	}

}

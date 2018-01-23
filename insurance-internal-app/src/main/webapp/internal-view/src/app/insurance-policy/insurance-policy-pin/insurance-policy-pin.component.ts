import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';

@Component({
  selector: 'app-insurance-policy-pin',
  templateUrl: './insurance-policy-pin.component.html',
  styleUrls: ['./insurance-policy-pin.component.css']
})
export class InsurancePolicyPinComponent implements OnInit {

	pinForm: FormGroup;

 	constructor() { }

  	ngOnInit() {
  		this.pinForm = new FormGroup({
            cardHolder: new FormControl('', {validators:[Validators.required]}),
            pin: new FormControl('',{validators: [Validators.required,Validators.pattern("[0-9]*"), 
            	Validators.maxLength(4), Validators.minLength(4)]}),
  		})
  	}

}

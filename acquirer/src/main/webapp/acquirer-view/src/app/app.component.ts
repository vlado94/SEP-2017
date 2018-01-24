import { Component, OnInit, EventEmitter, Input, Output } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Http, Response } from "@angular/http";
import { PaymentRequest } from './payment-request';
import { AppService } from './app.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
	   
	  
  myForm: FormGroup;
  holderName = new FormControl();
  cardNum = new FormControl();
  expDate = new FormControl();
  paymentRequest : PaymentRequest;
  cvv2 = new FormControl();
	 
   constructor(private appService: AppService){ }
	  
  ngOnInit() {
    this.myForm = new FormGroup({
			holderName: new FormControl('', [Validators.required]),
			cardNum: new FormControl('', [Validators.required]),
			expDate: new FormControl('', [Validators.required]),
			cvv2: new FormControl('', [Validators.required]),
		});
  }
  
  createForm() {
    this.myForm = new FormGroup({
			holderName: new FormControl('', [Validators.required]),
			cardNum: new FormControl('', [Validators.required]),
			expDate: new FormControl('', [Validators.required]),
			cvv2: new FormControl('', [Validators.required]),
		});
  }
  
  submitCardDataForm(){
		console.log("11111111111");
		let value = this.myForm.value;
		let paymentRequest = new PaymentRequest(value.holderName, value.cardNum , value.expDate , value.cvv2);
		this.appService.postPayment(paymentRequest);
		console.log(paymentRequest);
		
	}
  
}



  
  
  
  


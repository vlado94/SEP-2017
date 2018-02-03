import { Component, OnInit, EventEmitter, Input, Output } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Http, Response, URLSearchParams } from "@angular/http";
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

    policyID = new FormControl();
    policyPrice = new FormControl();
    originalPrice;
    insuranceId;
    cardResonse: string;
    load: boolean = false;
    constructor(private appService: AppService){ }

    ngOnInit() {


      var normalizedQueryString = "";
      if (window.location.search.indexOf('?') == 0)
        { normalizedQueryString = window.location.search.substring(1); } 
      else 
        { normalizedQueryString = window.location.search; } 
      let params = new URLSearchParams(normalizedQueryString);

      let id = params.get('id');
      let price = params.get('price');
      this.originalPrice = price;
      this.insuranceId = id;

      this.myForm = new FormGroup({
        holderName: new FormControl('', [Validators.required, Validators.minLength(3)]),
        cardNum: new FormControl('', [Validators.required, Validators.pattern("[0-9]*")]),
        expDate: new FormControl('', [Validators.required]),
        cvv2: new FormControl('', [Validators.required, Validators.pattern("[0-9][0-9][0-9][0-9]?")]),
        policyID: new FormControl(id, [Validators.required]),
        policyPrice: new FormControl(price, [Validators.required]),
      });

      console.log(id);
      console.log(price);



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
      this.load = true;
      console.log("11111111111");
      let value = this.myForm.value;
      let paymentRequest = new PaymentRequest(value.holderName, value.cardNum , value.expDate , value.cvv2, value.policyID, this.originalPrice);
      this.appService.postPayment(paymentRequest).subscribe(
          data => {
            this.cardResonse = data;
            if(this.cardResonse === "True"){
              window.location.href="http://localhost:4300/cardPayment";
              console.log("Uspesno placeno");
              this.load = false;
            } else {
              this.load = false;
            }

          }
        );
      console.log(paymentRequest);

    }
  
}









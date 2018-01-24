import { Component, OnInit, Input,Output,EventEmitter} from '@angular/core';
import { PaypalService } from './paypal.service';

@Component({
	selector: 'app-insurance-policy-checkout',
	templateUrl: './insurance-policy-checkout.component.html',
	styleUrls: ['./insurance-policy-checkout.component.scss']
})
export class InsurancePolicyCheckoutComponent implements OnInit {

	@Output() nextTab = new EventEmitter<number>();
	@Input() persons;
	@Input() insurancePrice;
	@Input() carInsurancePrice;
	@Input() homeInsurancePrice;
	@Input() currentInsurancePolicy;
	checkoutVar = null;
	sum: number = 0;
	goToUrl: string = null;

	constructor(private paypalService: PaypalService) { }

	ngOnInit() {
		
	}

	changePreviousTab(){
		this.nextTab.emit(3);
	}

	@Input()
	set checkout(value){
		if(value){
			this.checkoutVar = value;
			console.log("checkout " + JSON.stringify(this.checkoutVar));
		}
	}; 

	paypal(){
		//console.log(this.insurancePrice + this.carInsurancePrice + this.homeInsurancePrice);
		this.sum = this.insurancePrice + this.carInsurancePrice + this.homeInsurancePrice;
		console.log("suma je " + this.sum);
		this.currentInsurancePolicy.priceSum = this.sum;

		this.paypalService.paypal(this.currentInsurancePolicy).subscribe(data => {
			this.goToUrl = data;
			window.location.href = this.goToUrl;
		})
		console.log("paypal");
	}

}

import { Component, OnInit,  } from '@angular/core';
import {Router, ActivatedRoute, Params} from '@angular/router';
import { PaypalExecuteServiceService } from './paypal-execute-service.service';


@Component({
	selector: 'app-paypal-execute',
	templateUrl: './paypal-execute.component.html',
	styleUrls: ['./paypal-execute.component.scss']
})
export class PaypalExecuteComponent implements OnInit {

	executeSuccess: boolean = false;
	executeNotSuccess: boolean = false;
	PayerID: string = null
	paymentId: string = null;

	constructor(private paypalExecuteServiceService: PaypalExecuteServiceService, private activatedRoute: ActivatedRoute) { }

	ngOnInit() {
		this.activatedRoute.queryParams.subscribe((params: Params) => {
			this.PayerID = params['PayerID'];
			this.paymentId = params['paymentId'];
			console.log("payedId" + this.PayerID);
		});

		this.paypalExecuteServiceService.execute(this.PayerID, this.paymentId).subscribe(
			data => {if(data==true)
						this.executeSuccess = data;
					else 
						this.executeNotSuccess = true;
					}
			)
	}

}

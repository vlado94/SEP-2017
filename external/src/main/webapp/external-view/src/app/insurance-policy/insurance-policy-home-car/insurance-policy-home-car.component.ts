import { Component, OnInit, Output, EventEmitter, Input } from '@angular/core';
import { InsurancePolicyCar } from './policy-car';
import { InsurancePolicyHome } from './policy-home';
import { InsurancePolicyCarCalculatePriceRequest } from './policy-car-calculate-price-request';
import { InsurancePolicyHomeCalculatePriceRequest } from './policy-home-calculate-price-request';

@Component({
	selector: 'app-insurance-policy-home-car',
	templateUrl: './insurance-policy-home-car.component.html',
	styleUrls: ['./insurance-policy-home-car.component.scss']
})
export class InsurancePolicyHomeCarComponent implements OnInit {
	activeForm: string = null;
	@Input() insurancePolicyCar;
	@Input() insurancePolicyHome;
    @Input() carInsurancePrice;
    @Input() homeInsurancePrice;
	/*@Output() insurancePolicyCarChanged = new EventEmitter<InsurancePolicyCar>();
    @Output() insurancePolicyHomeChanged = new EventEmitter<InsurancePolicyHome>();*/
	@Output() nextTab = new EventEmitter<number>();
	@Output() previousTab = new EventEmitter<number>();
	@Output() insurancePolicyCarChanged = new EventEmitter<InsurancePolicyCar>();
    @Output() insurancePolicyHomeChanged = new EventEmitter<InsurancePolicyHome>();
    @Output() calculatePriceCar = new EventEmitter<InsurancePolicyCarCalculatePriceRequest>();
    @Output() calculatePriceHome = new EventEmitter<InsurancePolicyHomeCalculatePriceRequest>();

	constructor() { }

	ngOnInit() {
	}

	changeNextTab(){
		this.nextTab.emit(4);
	}

	changePreviousTab(){
		this.previousTab.emit(2);
	}

	showForm(value) {
        this.activeForm = value;
    }

    setInsurancePolicyCar(value) {
        this.insurancePolicyCarChanged.emit(value);
    }

    setInsurancePolicyHome(value) {
        this.insurancePolicyHomeChanged.emit(value);
        
    }

    calculatePriceCarInsurance(value) {
        this.calculatePriceCar.emit(value);
    }

    calculatePriceHomeInsurance(value) {
        this.calculatePriceHome.emit(value);
    }

}

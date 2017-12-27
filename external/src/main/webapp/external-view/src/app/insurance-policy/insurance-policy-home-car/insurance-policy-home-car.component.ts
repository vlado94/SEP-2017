import { Component, OnInit, Output, EventEmitter, Input } from '@angular/core';
import { InsurancePolicyCar } from './policy-car';
import { InsurancePolicyHome } from './policy-home';

@Component({
	selector: 'app-insurance-policy-home-car',
	templateUrl: './insurance-policy-home-car.component.html',
	styleUrls: ['./insurance-policy-home-car.component.scss']
})
export class InsurancePolicyHomeCarComponent implements OnInit {
	activeForm: string = null;
	@Input() insurancePolicyCar;

	/*@Output() insurancePolicyCarChanged = new EventEmitter<InsurancePolicyCar>();
    @Output() insurancePolicyHomeChanged = new EventEmitter<InsurancePolicyHome>();*/
	@Output() nextTab = new EventEmitter<number>();
	@Output() previousTab = new EventEmitter<number>();
	@Output() insurancePolicyCarChanged = new EventEmitter<InsurancePolicyCar>();
    @Output() insurancePolicyHomeChanged = new EventEmitter<InsurancePolicyHome>();

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
        if (value) {
            console.log("Dodavanje osigranja automobila...");
            console.log("Registaraska oznaka automobila: " + value.registrationNumber);
        }
    }

    setInsurancePolicyHome(value) {
        this.insurancePolicyHomeChanged.emit(value);
        if (value) {
            console.log("Dodavanje osigranja kuce...");
            console.log("Adresa kuce: " + value.address);
        }
    }

}

import {Component, OnInit, Input, Output, EventEmitter} from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import { InsurancePolicyCar } from '../policy-car';

@Component({
	selector: 'app-insurance-policy-car',
	templateUrl: './insurance-policy-car.component.html',
	styleUrls: ['./insurance-policy-car.component.scss']
})
export class InsurancePolicyCarComponent implements OnInit {
	insurancePolicyCarForm: FormGroup;
	@Output() setInsurancePolicyCar = new EventEmitter<InsurancePolicyCar>();
	@Output() hideForm = new EventEmitter<string>();
	currentCar: InsurancePolicyCar = null;

	constructor() { 
		this.insurancePolicyCarForm = new FormGroup({
			duration: new FormControl('', [
				Validators.required,
				Validators.pattern("[0-9]*")]),
			paket: new FormControl('', [
				Validators.required]),
			vehicle: new FormControl('', [Validators.required]),
			typeOfVehicle: new FormControl('', [Validators.required]),
			year: new FormControl('', [Validators.required, Validators.pattern("[0-9]{4}")]),
			registrationNumber: new FormControl('', [Validators.required]),
			chassisNumber: new FormControl('', [Validators.required]),
			firstName: new FormControl('', [Validators.required]),
			lastName: new FormControl('', [Validators.required]),
			jmbg: new FormControl('', [
				Validators.required,
				Validators.minLength(13),
				Validators.maxLength(13)
				]),
		})
	}

	ngOnInit() {
	}

	@Input()
	set insurancePolicyCar(value: InsurancePolicyCar) {
		this.currentCar = value;
		console.log("SETTOVANJE POLISE ZA AUTO");
		if (value) {
			this.insurancePolicyCarForm.setValue({
				duration: value.duration,
				paket: value.paket,
				vehicle: value.vehicle,
				typeOfVehicle: value.typeOfVehicle,
				year: value.year,
				registrationNumber: value.registrationNumber,
				chassisNumber: value.chassisNumber,
				firstName: value.firstName,
				lastName: value.lastName,
				jmbg: value.jmbg
			})
		}
		else {
			this.insurancePolicyCarForm.reset();
		}
	}

	submitCar() {
		
		console.log("auto value " + JSON.stringify(value))
		var value = this.insurancePolicyCarForm.value;
		var policyCar: InsurancePolicyCar = null;
		var policyCar = new InsurancePolicyCar(value.duration, value.paket, value.vehicle, value.typeOfVehicle, value.year, value.registrationNumber, value.chassisNumber, value.firstName, value.lastName, value.jmbg);
		this.setInsurancePolicyCar.emit(policyCar);

		this.hideForm.emit(null);

	}

	cancelCar(){
		this.setInsurancePolicyCar.emit(null);
		this.hideForm.emit(null);
	}

	closeForm() {
		this.hideForm.emit(null);
	}

}

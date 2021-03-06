import {Component, OnInit, Input, Output, EventEmitter} from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import { InsurancePolicyCar } from '../policy-car';
import { FactorService } from "../../../factor/factor.service";
import { Factor } from '../../../factor/factor';
import { InsurancePolicyCarCalculatePriceRequest } from '../policy-car-calculate-price-request';


@Component({
	selector: 'app-insurance-policy-car',
	templateUrl: './insurance-policy-car.component.html',
	styleUrls: ['./insurance-policy-car.component.scss']
})
export class InsurancePolicyCarComponent implements OnInit {
	insurancePolicyCarForm: FormGroup;
	@Output() setInsurancePolicyCar = new EventEmitter<InsurancePolicyCar>();
	@Output() hideForm = new EventEmitter<string>();
	@Output() calculatePrice = new EventEmitter<InsurancePolicyCarCalculatePriceRequest>();
	@Input() price;

	currentCar: InsurancePolicyCar = null;
	slepovanje_list: Factor[];
	popravka_list: Factor[];
	smestaj_list: Factor[];
	prevoz_list: Factor[];
	paket_selected: boolean = false;

	constructor(private factorService: FactorService) { 
		this.insurancePolicyCarForm = new FormGroup({
			duration: new FormControl('', [
				Validators.required,
				Validators.pattern("[0-9]*")]),
			slepovanje: new FormControl(''),
			popravka: new FormControl(''),
			smestaj: new FormControl(''),
			prevoz: new FormControl(''),
			vehicle: new FormControl('', [Validators.required]),
			typeOfVehicle: new FormControl('', [Validators.required]),
			year: new FormControl('', [Validators.required, Validators.pattern("[0-9]{4}"), Validators.min(1950), Validators.max(new Date().getFullYear())]),
			registrationNumber: new FormControl('', [Validators.required]),
			chassisNumber: new FormControl('', [Validators.required]),
			firstName: new FormControl('', [Validators.required]),
			lastName: new FormControl('', [Validators.required]),
			personNo: new FormControl('', [
				Validators.required,
				Validators.minLength(13),
				Validators.maxLength(13)
				]),
		})
	}

	ngOnInit() {

		this.insurancePolicyCarForm.reset();
        this.insurancePolicyCarForm.controls['slepovanje'].setValue('');
        this.insurancePolicyCarForm.controls['smestaj'].setValue('');
        this.insurancePolicyCarForm.controls['popravka'].setValue('');
        this.insurancePolicyCarForm.controls['prevoz'].setValue('');

		this.insurancePolicyCarForm.valueChanges.subscribe(value => {
			if (this.insurancePolicyCarForm.controls['duration'].valid &&
				(this.insurancePolicyCarForm.controls['slepovanje'].value != '' || this.insurancePolicyCarForm.controls['popravka'].value != ''
					|| this.insurancePolicyCarForm.controls['prevoz'].value != '' || this.insurancePolicyCarForm.controls['smestaj'].value != '')) {
				let insurancePolicyCarCalculatePriceRequest: InsurancePolicyCarCalculatePriceRequest = new InsurancePolicyCarCalculatePriceRequest(value.duration, value.slepovanje, value.smestaj, value.popravka, value.prevoz)
				this.calculatePrice.emit(insurancePolicyCarCalculatePriceRequest);
                this.paket_selected = true;
            } else {
                this.paket_selected = false;
                this.calculatePrice.emit(null);
            }
        })

		this.factorService.findByCategory(10)
		.subscribe(slepovanje => {
			this.slepovanje_list = slepovanje;
		})
		this.factorService.findByCategory(11)
		.subscribe(popravka => {
			this.popravka_list = popravka;
		})
		this.factorService.findByCategory(12)
		.subscribe(smestaj => {
			this.smestaj_list = smestaj;
		})
		this.factorService.findByCategory(13)
		.subscribe(prevoz => {
			this.prevoz_list = prevoz;
		})
	}

	@Input()
	set insurancePolicyCar(value: InsurancePolicyCar) {
		this.currentCar = value;
		if (value) {
			this.insurancePolicyCarForm.setValue({
				duration: value.duration,
				slepovanje: value.slepovanje,
				popravka: value.popravka,
				smestaj: value.smestaj,
				prevoz: value.prevoz,
				vehicle: value.vehicle,
				typeOfVehicle: value.typeOfVehicle,
				year: value.year,
				registrationNumber: value.registrationNumber,
				chassisNumber: value.chassisNumber,
				firstName: value.firstName,
				lastName: value.lastName,
				personNo: value.personNo
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
		policyCar = new InsurancePolicyCar(value.duration, value.slepovanje, value.prevoz, value.smestaj, value.popravka, value.vehicle, value.typeOfVehicle,
			value.year, value.registrationNumber, value.chassisNumber, value.firstName, value.lastName, value.personNo);
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

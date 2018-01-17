import { Component, OnInit, EventEmitter, Input, Output } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { BsDatepickerConfig } from 'ngx-bootstrap/datepicker';
import { InsurancePolicyRequest } from '../insurance-policy-request'
import { InsurancePolicyCalculatePriceRequest } from '../insurance-policy-calculate-price'
import { FactorService } from "../../factor/factor.service";
import { Factor } from '../../factor/factor';
import { InsurancePolicyService } from '../insurance-policy.service';


@Component({
	selector: 'app-insurance-policy-form',
	templateUrl: './insurance-policy-form.component.html',
	styleUrls: ['./insurance-policy-form.component.scss']
})
export class InsurancePolicyFormComponent implements OnInit {

	insurancePolicyForm: FormGroup;
	@Output() nextTab = new EventEmitter<number>();
	@Output() savePolicyRequest = new EventEmitter<InsurancePolicyRequest>();
	@Output() calculatePrice = new EventEmitter<InsurancePolicyCalculatePriceRequest>();
	@Input() currentInsurancePolicy;
	@Input() price;
	regions: Factor[];
	sports: Factor[];
	agesCategory: Factor[];
	amounts: Factor[];
	types: Factor[];

	constructor(private insurancePolicyService: InsurancePolicyService, private factorService: FactorService) { }

	ngOnInit() {
		this.insurancePolicyForm = new FormGroup({
			startDate: new FormControl('', [dateValidator]),
			duration: new FormControl('', [
				Validators.required,
				Validators.pattern("[0-9]*"),
				numberOfPersonsValidator]),
			typeOfPolicy: new FormControl('',[Validators.required]),
			numberOfPersonsUpTo16: new FormControl('', Validators.pattern("[0-9]*")),
			numberOfPersonsBetween16And60: new FormControl('', Validators.pattern("[0-9]*")),
			numberOfPersonsOver60: new FormControl('', Validators.pattern("[0-9]*")),
			region: new FormControl('', [Validators.required]),
			sport: new FormControl('', [Validators.required]),
			amount: new FormControl('', [Validators.required]),
		});
		console.log("aaaaaa");
		this.factorService.findByCategory(3)
		.subscribe(regions => {
			this.regions = regions;
		})

		this.factorService.findByCategory(1)
		.subscribe(sports => {
			this.sports = sports;
		})

		this.factorService.findByCategory(2)
		.subscribe(agesCategory => {
			this.agesCategory = agesCategory;
		})

		this.factorService.findByCategory(4)
		.subscribe(types => {
			this.types = types;
		})

		this.factorService.findByCategory(5)
		.subscribe(amounts => {
			this.amounts = amounts;
		})



		this.insurancePolicyForm.valueChanges.subscribe(value => {
           // console.log('Form changes', value)
           	if(this.insurancePolicyForm.controls['startDate'].value != "")
				document.getElementById("startDateLabel").classList.remove('is-empty');

			if(value.sport == "no sport")
				value.sport="";

           if (this.insurancePolicyForm.valid && this.checkNumberOfPeople()) {
                let insurancePolicyCalculatePriceRequest: InsurancePolicyCalculatePriceRequest = new InsurancePolicyCalculatePriceRequest(value.startDate, value.duration,
                    value.region, value.sport, value.amount, value.typeOfPolicy, +value.numberOfPersonsUpTo16, +value.numberOfPersonsBetween16And60, +value.numberOfPersonsOver60)

                this.calculatePrice.emit(insurancePolicyCalculatePriceRequest);
                console.log("proslo");
            } else {
            	this.price = null;
                //this.calculatePrice.emit(null);

            }
        })
	}

	submitPolicyForm(){
		let value = this.insurancePolicyForm.value;
		let insurancePolicyRequest = new InsurancePolicyRequest(value.startDate, value.duration,
			value.region, value.sport, value.amount, value.typeOfPolicy, +value.numberOfPersonsUpTo16, +value.numberOfPersonsBetween16And60, +value.numberOfPersonsOver60)
		this.savePolicyRequest.emit(insurancePolicyRequest);
		this.nextTab.emit(2);
	}


	colorTheme = 'theme-green';
	bsConfig: Partial<BsDatepickerConfig>;

	applyTheme(pop: any) {
	    // create new object on each property change
	    // so Angular can catch object reference change
	    this.bsConfig = Object.assign({}, { containerClass: this.colorTheme });
	    setTimeout(() => {
	    	pop.show();
	    });
	}


	checkNumberOfPeople() {
		let result = false;
		let upTo16: number = +this.insurancePolicyForm.get('numberOfPersonsUpTo16').value;
		let between16And60: number = +this.insurancePolicyForm.get('numberOfPersonsBetween16And60').value;
		let over60: number = +this.insurancePolicyForm.get('numberOfPersonsOver60').value;
		let sum: number = upTo16 + between16And60 + over60;

		if (sum > 0) 
			result = true;

		return result;
	}

	convertDate(d) {
		let parts = d.split('-');
		return new Date(+parts[0], +parts[1] - 1, parts[2]);
	}

	changedDate(){
		this.insurancePolicyForm.value['startDate']
		if(this.insurancePolicyForm.value)
			console.log("sss")
			
	}

}

function dateValidator(date: FormControl) {
	let input = date.value;
	if(input != ""){
		let month = input.getMonth()+1;
		let day = input.getDate();
		let year = input.getFullYear();

		let inputDate = new Date(year, month-1, day, 23, 59);
		let currentDate = new Date();
		
		if (inputDate < currentDate){
			return {
				dateValidator: {
					valid: false
				}
			}
		} else {
			return null;
		}
	}
}

function numberOfPersonsValidator(num: FormControl) {
	if ((+num.value) <= 0)
		return {
			numberOfPersonsValidator: {
				valid: true
			}
		}
		else
			return null;

	}

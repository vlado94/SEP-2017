import { Component, OnInit, EventEmitter, Input, Output } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { BsDatepickerConfig } from 'ngx-bootstrap/datepicker';
import { InsurancePolicyRequest } from '../insurance-policy-request'

@Component({
	selector: 'app-insurance-policy-form',
	templateUrl: './insurance-policy-form.component.html',
	styleUrls: ['./insurance-policy-form.component.scss']
})
export class InsurancePolicyFormComponent implements OnInit {

	insurancePolicyForm: FormGroup;
	@Output() nextTab = new EventEmitter<number>();
	@Output() savePolicyRequest = new EventEmitter<InsurancePolicyRequest>();
	@Input() currentInsurancePolicy;
	constructor() { }

	ngOnInit() {
		this.insurancePolicyForm = new FormGroup({
			startDate: new FormControl(''),
			duration: new FormControl('', [
				Validators.required,
				Validators.pattern("[0-9]*"),
				numberOfPersonsValidator]),
			typeOfPolicy: new FormControl(''),
			//age: new FormControl(''),
			numberOfPersons: new FormControl('', [
				Validators.required,
				Validators.pattern("[0-9]*"),numberOfPersonsValidator]
				),
			numberOfPersonsUpTo16: new FormControl(''),
			numberOfPersonsBetween16And60: new FormControl(''),
			numberOfPersonsOver60: new FormControl(''),
			region: new FormControl(''),
			sport: new FormControl('', [
				Validators.required
				]),
			amount: new FormControl('', [
				Validators.required
				]),
		});

		if(this.currentInsurancePolicy){
			
			var value = this.currentInsurancePolicy;

			this.insurancePolicyForm.setValue({
				startDate: value.startDate,
				duration: value.duration,
				typeOfPolicy: value.typeOfPolicy,
				numberOfPersons: value.numberOfPersons,
				numberOfPersonsUpTo16: value.firstAgeCategory,
				numberOfPersonsBetween16And60: value.secondAgeCategory,
				numberOfPersonsOver60: value.thirdAgeCategory,
				region: value.region,
				sport: value.sport,
				amount: value.amount
			})
		}

		this.insurancePolicyForm.valueChanges.subscribe(value => {
           // console.log('Form changes', value)
           if (this.insurancePolicyForm.valid && this.checkNumberOfPeople()) {
               /* let insurancePolicyCalculatePriceRequest: InsurancePolicyCalculatePriceRequest = new InsurancePolicyCalculatePriceRequest(value.startDate, value.duration,
                    value.region, value.sport, value.amount, value.typeOfPolicy, +value.numberOfPersons, +value.numberOfPersonsUpTo16, +value.numberOfPersonsBetween16And60, +value.numberOfPersonsOver60)

                this.insurancePolicyService.calculatePrice(insurancePolicyCalculatePriceRequest).subscribe(price => {
                    this.price = price;
                })*/
                console.log("proslo");
            } else {
                //this.price = null;
            }
        })
	}

	submitPolicyForm(){
		let value = this.insurancePolicyForm.value;
		let insurancePolicyRequest = new InsurancePolicyRequest(value.startDate, value.duration,
			value.region, value.sport, value.amount, value.typeOfPolicy, +value.numberOfPersons, +value.numberOfPersonsUpTo16, +value.numberOfPersonsBetween16And60, +value.numberOfPersonsOver60)
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

		let numOfPersons = +this.insurancePolicyForm.get('numberOfPersons').value;
		if (sum == numOfPersons) {
			result = true;
		}

		return result;
	}

	convertDate(d) {
		let parts = d.split('-');
		return new Date(+parts[0], +parts[1] - 1, parts[2]);
	}

}

function dateValidator(date: FormControl) {
	let input = date.value;
	if(input != ""){
		let month = input.getMonth()+1;
		let day = input.getDate();
		let year = input.getFullYear();
		//let parts = input.split('-');

		let inputDate = new Date(year-1, month-1, day);
		let currentDate = new Date();
		if (inputDate > currentDate)
			return {
				dateValidator: {
					valid: true
				}
			}
			else
				return null;
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

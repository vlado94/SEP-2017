import {Component, Input, Output, EventEmitter} from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import { InsurancePolicyHome } from '../policy-home';


@Component({
	selector: 'app-insurance-policy-home',
	templateUrl: './insurance-policy-home.component.html',
	styleUrls: ['./insurance-policy-home.component.scss']
})
export class InsurancePolicyHomeComponent {

	insurancePolicyHomeForm: FormGroup;
	@Input() current: InsurancePolicyHome;

	@Output() setInsurancePolicyHome = new EventEmitter<InsurancePolicyHome>();

	@Output() hideForm = new EventEmitter<string>();
	//@Output() calculatePrice = new EventEmitter<InsurancePolicyHomeCalculatePriceRequest>();
	//@Input() price;

	constructor() { 
		this.insurancePolicyHomeForm = new FormGroup({
            duration: new FormControl('', [
                Validators.required,
                Validators.pattern("[0-9]*")]),
            size: new FormControl('', [
                Validators.required]),
            age: new FormControl('', [
                Validators.required]),
            value: new FormControl('', [
                Validators.required]),
            risk: new FormControl('', [
                Validators.required]),
            address: new FormControl('', [
                Validators.required]),
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
	    if(this.current){
            var value = this.current;
            this.insurancePolicyHomeForm.setValue({
                duration: value.duration,
                size: value.size,
                age: value.age,
                risk: value.risk,
                value: value.value,
                address: value.address,
                firstName: value.firstName,
                lastName: value.lastName,
                jmbg: value.jmbg
            })
        }
    }


	@Input()
    set insurancePolicyHome(value: InsurancePolicyHome) {
        this.current = value;
        console.log("SETTOVANJE POLISE ZA KUCU");
        if (value) {
            this.insurancePolicyHomeForm.setValue({
                duration: value.duration,
                size: value.size,
                age: value.age,
                risk: value.risk,
                value: value.value,
                address: value.address,
                firstName: value.firstName,
                lastName: value.lastName,
                jmbg: value.jmbg
            })
        }
        else {
            this.insurancePolicyHomeForm.reset();
        }
    }

    submitHome(value) {
        var policyHome: InsurancePolicyHome = null;
        if (value != null) {
            policyHome = new InsurancePolicyHome(value.duration, value.size, value.age, value.value, value.risk, value.address, value.firstName, value.lastName, value.jmbg);
            this.setInsurancePolicyHome.emit(policyHome);
        } else {
            policyHome = this.insurancePolicyHomeForm.value;
            this.setInsurancePolicyHome.emit(policyHome);
        }
        this.hideForm.emit(null);
        this.insurancePolicyHomeForm.reset();
    }

    closeForm() {
        this.hideForm.emit(null);
    }

}

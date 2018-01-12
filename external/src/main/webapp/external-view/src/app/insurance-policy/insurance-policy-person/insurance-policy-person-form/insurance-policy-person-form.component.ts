import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { InsurancePolicyPersonRequest } from '../../insurance-policy-person-request'

@Component({
	selector: 'app-insurance-policy-person-form',
	templateUrl: './insurance-policy-person-form.component.html',
	styleUrls: ['./insurance-policy-person-form.component.scss']
})
export class InsurancePolicyPersonFormComponent implements OnInit {

	@Input() persons;
	@Output() addPerson = new EventEmitter<InsurancePolicyPersonRequest>();
	insurancePolicyPersonForm: FormGroup;
	contractorAdded: boolean = false;
    currentPerson: InsurancePolicyPersonRequest;

	constructor() { }

	ngOnInit() {
		this.insurancePolicyPersonForm = new FormGroup({
			firstName: new FormControl('', [
				Validators.required,
				Validators.minLength(3)
				]),
			lastName: new FormControl('', [
				Validators.required,
				Validators.minLength(3)
				]),
			personNo: new FormControl('', [
				Validators.required,
				Validators.minLength(13),
				Validators.maxLength(13)
				]),
			passportNo: new FormControl(''),

			address: new FormControl('', [
				Validators.required
				]),
			phoneNo: new FormControl('', [
				Validators.required
				]),
			contractor: new FormControl('false', [
				]),
			email: new FormControl('', [
				]),
		})

	}

	addPersonForm(){
		let value = this.insurancePolicyPersonForm.value;
		let newPerson: InsurancePolicyPersonRequest = new InsurancePolicyPersonRequest(value.firstName, value.lastName, value.personNo, value.passportNo,
            value.address, value.phoneNo, value.contractor, value.email);
		this.addPerson.emit(newPerson);
		this.contractorExists();
		this.insurancePolicyPersonForm.reset();
	}

	contractorExists() {
        let result = false;
        for (let person of this.persons) {
        	if(person.contractor){
	            if (person.contractor.toString() == "true") {
	                result = true;
	                return result;
	            }
	        }
        }

        return result;
    }

    @Input()
    set current(person: InsurancePolicyPersonRequest) {
        this.currentPerson = person;
        console.log("ajdeeeeee");
        if (person) {
        	//if(this.currentPerson.contractor)
	            this.insurancePolicyPersonForm.setValue({
	                firstName: this.currentPerson.firstName,
	                lastName: this.currentPerson.lastName,
	                personNo: this.currentPerson.personNo,
	                passportNo: this.currentPerson.passportNo,
	                address: this.currentPerson.address,
	                phoneNo: this.currentPerson.phoneNo,
	                contractor: this.currentPerson.contractor,
	                email: this.currentPerson.email
	            })
	       
        }

    }

}

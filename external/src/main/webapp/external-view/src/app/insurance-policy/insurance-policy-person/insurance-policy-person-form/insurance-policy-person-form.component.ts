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

		//this.checkCurrentPerson();

		//this.contractorExists();
	}

/*	currentPersonExist(){
		if(this.currentPerson){
			var value = this.currentPerson;
			this.insurancePolicyPersonForm.setValue({
				firstName: value.fistName,
				lastName: value.lastName,
				personNo: value.personNo,
				passportNo: value.passportNo,
				address: value.address,
				phoneNo: value.phoneNo,
				contractor: value.contractor.toString(),
				email: value.email
			})
		}
			console.log("current person " + this.currentPerson.value);

	}
*/
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
            if (person.contractor == "true") {
                result = true;
            }
        }
       /* if (result)
            console.log("Podaci za ugovaraca polise su uneti.")
        else
            console.log("Podaci za ugovaraca polise nisu uneti.")*/

        return result;
    }

    @Input()
    set current(person: InsurancePolicyPersonRequest) {
        this.currentPerson = person;
        console.log("set current" + JSON.stringify(this.currentPerson))
        if (person) {
            this.insurancePolicyPersonForm.setValue({
                firstName: this.currentPerson.firstName,
                lastName: this.currentPerson.lastName,
                personNo: this.currentPerson.personNo,
                passportNo: this.currentPerson.passportNo,
                address: this.currentPerson.address,
                phoneNo: this.currentPerson.phoneNo,
                contractor: this.currentPerson.contractor.toString(),
                email: this.currentPerson.email
            })
        }

    }

}

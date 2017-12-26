import { Component, OnInit, Output, EventEmitter, Input } from '@angular/core';
import { InsurancePolicyPersonRequest } from "../insurance-policy-person-request"

@Component({
	selector: 'app-insurance-policy-person',
	templateUrl: './insurance-policy-person.component.html',
	styleUrls: ['./insurance-policy-person.component.scss']
})
export class InsurancePolicyPersonComponent implements OnInit {

	currentPerson: InsurancePolicyPersonRequest;
	persons: InsurancePolicyPersonRequest[] = [];
	@Output() nextTab = new EventEmitter<number>();
	@Output() previousTab = new EventEmitter<number>();
	

	constructor() { }

	ngOnInit() {

	}

	addPerson(value: InsurancePolicyPersonRequest){
		this.persons.push(value);
		console.log("ubacen korisnik");
	}

	selectForUpdate(insurancePolicyPersonRequest: InsurancePolicyPersonRequest){
		console.log("policy edit");
		this.currentPerson = insurancePolicyPersonRequest;
	}

	deletePerson(insurancePolicyPersonRequest: InsurancePolicyPersonRequest){
		let index = this.persons.indexOf(insurancePolicyPersonRequest);
		if(index != -1){
			this.persons.splice(index, 1);
		}
	}

	changeNextTab(){
		this.nextTab.emit(3);
	}

	changePreviousTab(){
		this.previousTab.emit(1);
	}


}

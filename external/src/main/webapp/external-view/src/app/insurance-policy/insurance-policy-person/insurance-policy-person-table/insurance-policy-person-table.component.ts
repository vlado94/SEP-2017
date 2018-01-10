import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { InsurancePolicyPersonRequest } from "../../insurance-policy-person-request";

@Component({
	selector: 'app-insurance-policy-person-table',
	templateUrl: './insurance-policy-person-table.component.html',
	styleUrls: ['./insurance-policy-person-table.component.scss']
})
export class InsurancePolicyPersonTableComponent implements OnInit {

	@Input() persons;
	@Output() deletePerson = new EventEmitter<InsurancePolicyPersonRequest>();
	@Output() selectForupdate = new EventEmitter<InsurancePolicyPersonRequest>();

	constructor() { }

	ngOnInit() {
		if(this.persons)
			console.log("persons " + this.persons);
	}

	deletePersonFromList(person: InsurancePolicyPersonRequest){
		this.deletePerson.emit(person);
	}

	updatePersonFromList(person: InsurancePolicyPersonRequest){
		this.selectForupdate.emit(person);
	}



}

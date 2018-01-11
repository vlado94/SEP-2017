import { Component, OnInit, Output, EventEmitter, Input } from '@angular/core';
import { InsurancePolicyPersonRequest } from "../insurance-policy-person-request"

@Component({
	selector: 'app-insurance-policy-person',
	templateUrl: './insurance-policy-person.component.html',
	styleUrls: ['./insurance-policy-person.component.scss']
})
export class InsurancePolicyPersonComponent{

	@Input() expectedNumbersByAgeCategories;
	currentPerson: InsurancePolicyPersonRequest;
	@Input() persons: InsurancePolicyPersonRequest[] = [];
	@Output() nextTab = new EventEmitter<number>();
	@Output() previousTab = new EventEmitter<number>();
	@Output() addPerson = new EventEmitter<InsurancePolicyPersonRequest[]>();
	@Output() updatePerson = new EventEmitter<InsurancePolicyPersonRequest[]>();
	under16: number = 0;
    between16an60: number = 0;
    over16: number = 0;
    firstCategoryOfAge = 16;
    secondCategoryOfAge = 60;
	

	constructor() { }



	pushPerson(value: InsurancePolicyPersonRequest){
		
		let age: number = this.getAgeFromPersonNo(value.personNo);
		console.log("push person");
		if(this.currentPerson){
			let index = this.persons.indexOf(this.currentPerson);
			//this.persons.splice(index, 1);
			let ageFromUpdate: number = this.getAgeFromPersonNo(this.currentPerson.personNo);
			console.log("doslo je do update-a");
			this.persons[index] = value;
			//this.persons.push(value);
			this.decreasePersonAgeCategory(ageFromUpdate);
            this.increasePersonAgeCategory(age);
            this.currentPerson = null;
            this.updatePerson.emit(this.persons);
            return;

		}
		this.persons.push(value);
		this.addPerson.emit(this.persons);
		//this.isNumberOfPersonsByAgeCorrect();
		this.increasePersonAgeCategory(this.getAgeFromPersonNo(value.personNo));
	}

	selectForUpdate(insurancePolicyPersonRequest: InsurancePolicyPersonRequest){
		this.currentPerson = insurancePolicyPersonRequest;
	}

	deletePerson(insurancePolicyPersonRequest: InsurancePolicyPersonRequest){
		let index = this.persons.indexOf(insurancePolicyPersonRequest);
		if(index != -1){
			this.persons.splice(index, 1);
			this.decreasePersonAgeCategory(this.getAgeFromPersonNo(insurancePolicyPersonRequest.personNo));
		}
	}

	changeNextTab(){
		this.nextTab.emit(3);
	}

	changePreviousTab(){
		this.previousTab.emit(1);
	}

	getAgeFromPersonNo(personNo: string) {
		let yearPersonNo: string = personNo.substr(4, 3);
		let year: number;
		if (yearPersonNo.charAt(0) == '0')
			year = +("2" + yearPersonNo);
		else {
			year = +("1" + yearPersonNo);
		}
		let now: number = new Date().getFullYear();
		let age = now - year;
		return age;
	}

	increasePersonAgeCategory(age: number) {
		if (age < this.firstCategoryOfAge) {
			this.under16++;
		} else if (this.firstCategoryOfAge < age && age < this.secondCategoryOfAge) {
			this.between16an60++;
		} else if (age > this.secondCategoryOfAge) {
			this.over16++;
		}
	}

	decreasePersonAgeCategory(age: number) {
        if (age < this.firstCategoryOfAge) {
            this.under16--;
        } else if (this.firstCategoryOfAge < age && age < this.secondCategoryOfAge) {
            this.between16an60--;
        } else if (age > this.secondCategoryOfAge) {
            this.over16--;
        }
    }

    isNumberOfPersonsByAgeCorrect() {
        
        let result: boolean = false;
        if (this.under16 == this.expectedNumbersByAgeCategories.firstCategory && this.between16an60 == this.expectedNumbersByAgeCategories.secondCategory && this.over16 == this.expectedNumbersByAgeCategories.thirdCategory) {
            result = true;
            return result;
        }
        return result;
    }

    isContractorAdded() {
        let result: boolean = false;
        for (let person of this.persons) {
        	if(person.contractor){
	            if (person.contractor.toString() == "true") {
	                result = true;
	                break;
	            }
	        }
        }
        return result;
    }


}

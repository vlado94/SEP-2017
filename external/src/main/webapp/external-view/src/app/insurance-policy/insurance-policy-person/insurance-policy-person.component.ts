import { Component, OnInit, Output, EventEmitter, Input } from '@angular/core';
import { InsurancePolicyPersonRequest } from "../insurance-policy-person-request"

@Component({
	selector: 'app-insurance-policy-person',
	templateUrl: './insurance-policy-person.component.html',
	styleUrls: ['./insurance-policy-person.component.scss']
})
export class InsurancePolicyPersonComponent implements OnInit {

	@Input() expectedNumbersByAgeCategories;
	currentPerson: InsurancePolicyPersonRequest;
	persons: InsurancePolicyPersonRequest[] = [];
	@Output() nextTab = new EventEmitter<number>();
	@Output() previousTab = new EventEmitter<number>();
	under16: number = 0;
    between16an60: number = 0;
    over16: number = 0;
    firstCategoryOfAge = 16;
    secondCategoryOfAge = 60;
	

	constructor() { }

	ngOnInit() {

	}

	addPerson(value: InsurancePolicyPersonRequest){
		this.persons.push(value);
		this.getAgeFromPersonNo(value.personNo);
		console.log("ubacen korisnik");
		//this.isNumberOfPersonsByAgeCorrect();
		this.increasePersonAgeCategory(this.getAgeFromPersonNo(value.personNo));
	}

	selectForUpdate(insurancePolicyPersonRequest: InsurancePolicyPersonRequest){
		console.log("policy edit");
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
			console.log("Dodat korisnik ispod 16 godina")
			console.log("Trenutan broj korisnika ispod 16 godina: " + this.under16);
		} else if (this.firstCategoryOfAge < age && age < this.secondCategoryOfAge) {
			this.between16an60++;
			console.log("Dodat korisnik izmedju 16 i 60 godina")
			console.log("Trenutan broj korisnika izmedju 16 i 60 godina: " + this.between16an60);
		} else if (age > this.secondCategoryOfAge) {
			this.over16++;
			console.log("Dodat korisnik preko 60 godina")
			console.log("Trenutan broj korisnika preko 60: " + this.over16);
		}
	}

	decreasePersonAgeCategory(age: number) {
        if (age < this.firstCategoryOfAge) {
            this.under16--;
            console.log("Obrisan korisnik ispod 16 godina")
            console.log("Trenutan broj korisnika ispod 16 godina: " + this.under16);
        } else if (this.firstCategoryOfAge < age && age < this.secondCategoryOfAge) {
            this.between16an60--;
            console.log("Obrisan korisnik izmedju 16 i 60 godina")
            console.log("Trenutan broj korisnika izmedju 16 i 60 godina: " + this.between16an60);
        } else if (age > this.secondCategoryOfAge) {
            this.over16--;
            console.log("Obrisan korisnik preko 60 godina")
            console.log("Trenutan broj korisnika preko 60: " + this.over16);
        }
    }

    isNumberOfPersonsByAgeCorrect() {
        //console.log("Provera broja korisnika po godinama...")
        let result: boolean = false;
        if (this.under16 == this.expectedNumbersByAgeCategories.firstCategory && this.between16an60 == this.expectedNumbersByAgeCategories.secondCategory && this.over16 == this.expectedNumbersByAgeCategories.thirdCategory) {
            result = true;
            console.log("doboljno korisnika");
        }
       /* if (result) {
            console.log("Broj korisnika po godinama se poklapa sa unetim brojem korisnika po godinama...")

        } else
            console.log("Broj korisnika po godinama se ne poklapa sa unetim brojem korisnika po godinama...")*/

        return result;
    }

    isContractorAdded() {
        let result: boolean = false;
        for (let person of this.persons) {
            if (person.contractor.toString() == "true") {
            	console.log("person contactor " + person.contractor + " " + person.firstName);
                result = true;
                break;
            }
        }
        return result;
    }


}

import {Component, Output, EventEmitter, Input} from '@angular/core';

import {InsurancePolicyPersonRequest} from './insurance-policy-person-form/insurance-policy-person-form.component';

@Component({
    selector: 'app-insurance-policy-person',
    templateUrl: './insurance-policy-person.component.html',
    styleUrls: ['./insurance-policy-person.component.css']
})

export class InsurancePolicyPersonComponent {

    @Input() expectedNumbersByAgeCategories;
    currentPerson: InsurancePolicyPersonRequest;
    persons: InsurancePolicyPersonRequest[] = [];
    @Output() nextTab = new EventEmitter<string>();
    @Output() personsChanged = new EventEmitter<InsurancePolicyPersonRequest[]>();
    contractorExists:boolean = false;
    isNumberOfPersonsByAgeCorrect:boolean = false;
    firstCategoryOfAge = 16;
    secondCategoryOfAge = 60;
    under16: number = 0;
    between16an60: number = 0;
    over16: number = 0;

    changeTab(value: string) {
        this.nextTab.emit(value);
    }

    onSubmit(value: InsurancePolicyPersonRequest) {
        if(value.contractor){
            this.contractorExists = true;
        }
        let age: number = this.getAgeFromJmbg(value.personNo)
        if (this.currentPerson) {
            if(this.currentPerson.contractor && !value.contractor){
                this.contractorExists = false;
            }
            let index = this.persons.indexOf(this.currentPerson);
            this.persons.splice(index, 1, value);
            let ageFromUpdate: number = this.getAgeFromJmbg(this.currentPerson.personNo)

            this.decreasePersonAgeCategory(ageFromUpdate);
            this.increasePersonAgeCategory(age);
            this.currentPerson = null;
            this.personsChanged.emit(this.persons);
            
            this.isNumberOfPersonsByAgeCorrect = this.isNumberOfPersonsCorrect();
            return;
        }
        this.persons.push(value);
        this.increasePersonAgeCategory(age);
        this.personsChanged.emit(this.persons);
        this.isNumberOfPersonsByAgeCorrect = this.isNumberOfPersonsCorrect();

    }

    setForUpdate(value: InsurancePolicyPersonRequest) {
        this.currentPerson = value;
    }

    delete(value: InsurancePolicyPersonRequest) {
        let index = this.persons.indexOf(value);
        if (index != -1) {
            if(value.contractor){
                this.contractorExists = false;
            }
            this.persons.splice(index, 1);
        }
        if (this.currentPerson == value)
            this.currentPerson = null;
        this.decreasePersonAgeCategory(this.getAgeFromJmbg(value.personNo));
        this.isNumberOfPersonsByAgeCorrect = this.isNumberOfPersonsCorrect();
        this.personsChanged.emit(this.persons);
    }
    resetCurrent(value: InsurancePolicyPersonRequest) {
        this.currentPerson = value;
    }

    getAgeFromJmbg(jmbg: string) {
        let yearJmbg: string = jmbg.substr(4, 3);
        let year: number;
        if (yearJmbg.charAt(0) == '0')
            year = +("2" + yearJmbg);
        else {
            year = +("1" + yearJmbg);
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

    isNumberOfPersonsCorrect() {
        console.log("Provera broja korisnika po godinama...")
        let result: boolean = false;
        if (this.under16 == this.expectedNumbersByAgeCategories.firstCategory && this.between16an60 == this.expectedNumbersByAgeCategories.secondCategory && this.over16 == this.expectedNumbersByAgeCategories.thirdCategory) {
            result = true;
        }
        if (result) {
            console.log("Broj korisnika po godinama se poklapa sa unetim brojem korisnika po godinama...")

        } else
            console.log("Broj korisnika po godinama se ne poklapa sa unetim brojem korisnika po godinama...")

        return result;
    }

}
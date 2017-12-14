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
    firstCategoryOfAge = 16;
    secondCategoryOfAge = 60;
    under16: number = 0;
    between16an60: number = 0;
    over16: number = 0;
    changeTab(value: string) {
        this.nextTab.emit(value);
    }

    onSubmit(value: InsurancePolicyPersonRequest) {
        console.log("Submitting in parent");
        let age: number = this.getAgeFromJmbg(value.jmbg)
        if (this.currentPerson) {
            let index = this.persons.indexOf(this.currentPerson);
            this.persons.splice(index, 1, value);
            let ageFromUpdate: number = this.getAgeFromJmbg(this.currentPerson.jmbg)

            this.decreasePersonAgeCategory(ageFromUpdate);
            this.increasePersonAgeCategory(age);
            this.currentPerson = null;

            return;
        }
        this.persons.push(value);
        console.log(this.getAgeFromJmbg(value.jmbg));
        this.increasePersonAgeCategory(age);
    }

    setForUpdate(value: InsurancePolicyPersonRequest) {
        this.currentPerson = value;
    }

    delete(value: InsurancePolicyPersonRequest) {
        let index = this.persons.indexOf(value);
        if (index != -1) {
            this.persons.splice(index, 1);
        }
        if (this.currentPerson == value)
            this.currentPerson = null;
        this.decreasePersonAgeCategory(this.getAgeFromJmbg(value.jmbg));
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

    isContractorAdded() {
        let result: boolean = false;
        for (let person of this.persons) {
            if (person.contractor) {
                result = true;
            }
        }
        return result;
    }
}
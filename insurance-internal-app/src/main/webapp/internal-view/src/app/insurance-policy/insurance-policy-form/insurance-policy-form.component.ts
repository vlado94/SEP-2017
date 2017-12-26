import {Component, OnInit, Input, Output, EventEmitter} from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {Router} from '@angular/router';
import { NgModule } from '@angular/core';
import {InsurancePolicyService} from '../insurance-policy.service';
import {InsurancePolicyPersonRequest} from '../insurance-policy-person/insurance-policy-person-form/insurance-policy-person-form.component';

import { FactorService } from "../../factor/factor.service";
import { Factor } from '../../factor/factor';

function dateValidator(date: FormControl) {
    let input = date.value;
    let parts = input.split('-');

    let inpuDate = new Date(+parts[0], +parts[1] - 1, parts[2], 23, 59);
    let currentDate = new Date();
    if (inpuDate < currentDate)
        return {
            dateValidator: {
                valid: false
            }
        }
    else
        return null;
}

function numberOfPersonsValidator(num: FormControl) {
    if ((+num.value) <= 0)
        return {
            numberOfPersonsValidator: {
                valid: false
            }
        }
    else
        return null;

}
@Component({
    selector: 'app-insurance-policy-form',
    templateUrl: './insurance-policy-form.component.html',
    styleUrls: ['./insurance-policy-form.component.css'],

})
@NgModule({
    imports: [
    ],
    exports: [ // components that we want to make available
    ],
    declarations: [ // components for use in THIS module
    ],
    providers: [ // singleton services
        InsurancePolicyService,
        FactorService
    ]
})
export class InsurancePolicyFormComponent {

    insurancePolicy: FormGroup;
    contractorAdded: boolean = false;
    request: InsurancePolicyRequest;
    public addPersonButton: boolean = true;
    @Output() onFormSubmit = new EventEmitter<InsurancePolicyRequest>();
    @Output() nextTab = new EventEmitter<string>();
    price: string = null;
    regions: Factor[];
    sports: Factor[];
    agesCategory: Factor[];

    constructor(private insurancePolicyService: InsurancePolicyService, private factorService: FactorService) { }


    ngOnInit() {
        this.insurancePolicy = new FormGroup({
            startDate: new FormControl('', [dateValidator]),
            duration: new FormControl('', [
                Validators.required,
                Validators.pattern("[0-9]*"),
                numberOfPersonsValidator]),
            typeOfPolicy: new FormControl(''),
            age: new FormControl(''),
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

        this.factorService.findByCategory(3)
            .subscribe(regions => {
                this.regions = regions;
            })
        this.factorService.findByCategory(1)
            .subscribe(sports => {
                this.sports = sports;
            })

        this.factorService.findByCategory(2)
            .subscribe(agesCategory => {
                this.agesCategory = agesCategory;
            })
        this.insurancePolicy.valueChanges.subscribe(value => {
            console.log('Form changes', value)
            if (this.insurancePolicy.valid && this.checkNumberOfPeople()) {
                let insurancePolicyCalculatePriceRequest: InsurancePolicyCalculatePriceRequest = new InsurancePolicyCalculatePriceRequest(value.startDate, value.duration,
                    value.region, value.sport, value.amount, value.typeOfPolicy, +value.numberOfPersonsUpTo16, +value.numberOfPersonsBetween16And60, +value.numberOfPersonsOver60)

                this.insurancePolicyService.calculateSuggestedPrice(insurancePolicyCalculatePriceRequest).subscribe(price => {
                    this.price = price;
                })
            } else {
                this.price = null;
            }
        })

    }
    onSubmit({value}: { value }) {
        let insurancePolicyRequest = new InsurancePolicyRequest(value.startDate, value.duration,
            value.region, value.sport, value.amount, value.typeOfPolicy, +value.numberOfPersons, +value.numberOfPersonsUpTo16, +value.numberOfPersonsBetween16And60, +value.numberOfPersonsOver60)
        this.onFormSubmit.emit(insurancePolicyRequest);
        console.log('Submitting first page...');
        this.nextTab.emit('2');
    }

    checkNumberOfPeople() {
        let result = false;
        let upTo16: number = +this.insurancePolicy.get('numberOfPersonsUpTo16').value;
        let between16And60: number = +this.insurancePolicy.get('numberOfPersonsBetween16And60').value;
        let over60: number = +this.insurancePolicy.get('numberOfPersonsOver60').value;
        let sum: number = upTo16 + between16And60 + over60;

        //let numOfPersons = +this.insurancePolicy.get('numberOfPersons').value;
        if (sum > 0) {
            result = true;
            console.log("Valid");
        }
        if (result)
            console.log("Valid");

        else
            console.log("Invalid");

        return result;
    }

    /*checkDates() {
        console.log("Provera ispravnosti datuma...");
        let s = this.insurancePolicy.get('startDate').value
        let e = this.insurancePolicy.get('endDate').value
        let start = this.convertDate(s);
        let end = this.convertDate(e);
        console.log("Uneti datum pocetka putovanja:" + start);
        //console.log("Tranformisan datu");

        console.log("Uneti datum okoncanja putovanja:" + end);
        if (start < end) {
            console.log("Datumi su validni");
        } else {
            console.log("Datumi nisu validni");
        }
        return start < end;
    }*/
    //today | date:'fullDate'

    convertDate(d) {
        let parts = d.split('-');
        return new Date(+parts[0], +parts[1] - 1, parts[2]);
    }
}

export class InsurancePolicyCalculatePriceRequest {
    startDate: string;
    duration: number;
    region: string;
    sport: string;
    amount: string;
    typeOfPolicy: string;
    firstAgeCategory: number;
    secondAgeCategory: number;
    thirdAgeCategory: number;

    constructor(startDate: string, duration: number,
        region: string, sportId: string,
        amountId: string, typeOfPolicy: string,
        firstAgeCategory: number,
        secondAgeCategory: number,
        thirdAgeCategory: number) {
        this.startDate = startDate;
        this.duration = duration;
        this.region = region;
        this.sport = sportId;
        this.amount = amountId;
        this.firstAgeCategory = firstAgeCategory;
        this.secondAgeCategory = secondAgeCategory;
        this.thirdAgeCategory = thirdAgeCategory;
        this.typeOfPolicy = typeOfPolicy;
    }
}
export class InsurancePolicyRequest {
    startDate: string;
    duration: number;
    region: string;
    sport: string;
    amount: string;
    typeOfPolicy: string;
    numberOfPersons: number;
    firstAgeCategory: number;
    secondAgeCategory: number;
    thirdAgeCategory: number;

    persons: InsurancePolicyPersonRequest[] = [];
    constructor(startDate: string, duration: number,
        region: string, sportId: string,
        amountId: string, typeOfPolicy: string, numberOfPersons: number,
        firstAgeCategory: number,
        secondAgeCategory: number,
        thirdAgeCategory: number) {
        this.startDate = startDate;
        this.duration = duration;
        this.region = region;
        this.sport = sportId;
        this.amount = amountId;
        this.numberOfPersons = numberOfPersons;
        this.firstAgeCategory = firstAgeCategory;
        this.secondAgeCategory = secondAgeCategory;
        this.thirdAgeCategory = thirdAgeCategory;
        this.typeOfPolicy = typeOfPolicy;
    }
}

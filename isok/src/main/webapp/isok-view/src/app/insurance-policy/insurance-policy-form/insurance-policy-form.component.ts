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

    let inpuDate = new Date(+parts[0], +parts[1] - 1, parts[2],23,59);
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
    @Input() currentPerson;

    regions: Factor[];
    sports: Factor[];
    @Input() persons;
    @Input() formPerson;
    @Output() toggleFormPersonEmitter = new EventEmitter<boolean>();
    constructor(private insurancePolicyService: InsurancePolicyService, private factorService: FactorService) { }


    ngOnInit() {

        this.insurancePolicy = new FormGroup({
            startDate: new FormControl('',[dateValidator]),
            endDate: new FormControl('',[dateValidator]),
            duration: new FormControl('', [
                Validators.required,
                Validators.pattern("[0-9]*")]),
            typeOfPolicy: new FormControl(''),
            numberOfPersons: new FormControl('', [
                Validators.pattern("[0-9]*")]),
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
    }
    onSubmit({value}: { value: InsurancePolicyRequest }) {
        let insurancePolicyRequest = new InsurancePolicyRequest(value.startDate, value.endDate, value.duration,
            value.region, value.sport, value.amount, this.persons)
        this.insurancePolicyService.create(insurancePolicyRequest)
            .subscribe(insurancePolicy => console.log("123"));
    }

    toggleFormPerson(value: boolean) {
        this.toggleFormPersonEmitter.emit(value);
        this.addPersonButton = !value;
    }

    checkNumberOfPeople() {
        if (+this.insurancePolicy.get('typeOfPolicy').value == 1)
            return true;
        let result = false;
        let upTo16: number = +this.insurancePolicy.get('numberOfPersonsUpTo16').value;
        let between16And60: number = +this.insurancePolicy.get('numberOfPersonsBetween16And60').value;
        let over60: number = +this.insurancePolicy.get('numberOfPersonsOver60').value;
        let sum: number = upTo16 + between16And60 + over60;

        let numOfPersons = +this.insurancePolicy.get('numberOfPersons').value;
        if (numOfPersons > 1 && sum == numOfPersons) {
            result = true;
            console.log("Valid");
        }
        if (result)
            console.log("Valid");

        else
            console.log("Invalid");

        return result;
    }

    checkDates() {
        let s = this.insurancePolicy.get('startDate').value
        let e = this.insurancePolicy.get('endDate').value
        console.log(s);
        let start = this.convertDate(s);
        let end = this.convertDate(e);
        console.log("Start:" + start);
        console.log(e);

        console.log("End:" + end);
        console.log(start < end);
        return start < end;
    }
    //today | date:'fullDate'

    convertDate(d) {
        let parts = d.split('-');

        return new Date(+parts[0], +parts[1] - 1, parts[2]);
    }
}

export class InsurancePolicyRequest {
    startDate: string;
    endDate: string;
    duration: number;
    region: string;
    sport: string;
    amount: string;
    persons: InsurancePolicyPersonRequest[] = [];
    constructor(startDate: string, endDate: string, duration: number,
        region: string, sportId: string,
        amountId: string, persons: InsurancePolicyPersonRequest[]) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.duration = duration;
        this.region = region;
        this.persons = persons;
        this.sport = sportId;
        this.amount = amountId;
    }
}

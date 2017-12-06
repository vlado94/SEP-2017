import {Component, OnInit, Input, Output, EventEmitter} from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {Router} from '@angular/router';
import { NgModule } from '@angular/core';
import {InsurancePolicyService} from '../insurance-policy.service';
import {InsurancePolicyPersonRequest} from '../insurance-policy-person/insurance-policy-person-form.component';

import { FactorService } from "../../factor/factor.service";
import { Factor } from '../../factor/factor';


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
            startDate: new FormControl(''),
            endDate: new FormControl(''),
            duration: new FormControl(''),
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

    checkIfContractorExists() {
        let x = false;
        for (let person of this.persons) {
            if (person.contractor) {
                x = true;
                break;
            }
        }
        return x;
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

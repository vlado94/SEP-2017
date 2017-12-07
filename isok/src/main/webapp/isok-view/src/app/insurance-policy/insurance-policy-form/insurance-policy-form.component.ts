import {Component, OnInit, Input} from '@angular/core';
import {FormControl, FormGroup} from '@angular/forms';
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
    public submitted: boolean;
    request: InsurancePolicyRequest;
    public personForm: boolean = false;
    public addPersonButton: boolean = true;
    persons: InsurancePolicyPersonRequest[] = [];
    regions: Factor[];
    constructor(private insurancePolicyService: InsurancePolicyService, private factorService: FactorService) { }


    ngOnInit() {
        this.insurancePolicyService.personForm.subscribe
            (updatedValue => {
                this.personForm = updatedValue;
                this.addPersonButton = true;
            })
        this.insurancePolicy = new FormGroup({
            startDate: new FormControl(''),
            endDate: new FormControl(''),
            duration: new FormControl(''),
            region: new FormControl(''),
            amount: new FormControl(''),
        });
        this.insurancePolicyService.todos.subscribe
            (updatedTodos => {
                this.persons = updatedTodos;
            });
        this.factorService.findByCategory(3)
            .subscribe(regions => {
                this.regions = regions;
            })
    }
    onSubmit() {
        console.log(this.persons[0].firstName);
        console.log("123");
    }

    showPersonForm() {
        this.personForm = true;
        this.addPersonButton = false;
    }
}



export class InsurancePolicyRequest {
    firstName: string;
    lastName: string;

    constructor(firstName: string, lastName: string) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}

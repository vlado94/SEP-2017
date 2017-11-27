import {Component, OnInit, Input} from '@angular/core';
import {FormControl, FormGroup} from '@angular/forms';
import {Router} from '@angular/router';
import { NgModule } from '@angular/core';
import {InsurancePolicyService} from '../insurance-policy.service';
import {InsurancePolicyPersonRequest} from '../insurance-policy-person/insurance-policy-person-form.component';


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
    ]
})
export class InsurancePolicyFormComponent {

    insurancePolicy: FormGroup;
    public submitted: boolean;
    request: InsurancePolicyRequest;

    persons: InsurancePolicyPersonRequest[] = [];
    constructor(private insurancePolicyService: InsurancePolicyService) { }


    ngOnInit() {
        this.insurancePolicy = new FormGroup({
            startDate: new FormControl(''),
            endDate: new FormControl(''),
            duration: new FormControl(''),
            region: new FormControl(''),
            amount: new FormControl(''),
        })
        this.insurancePolicyService.todos.subscribe(updatedTodos => {
            this.persons = updatedTodos;
        });
    }
    onSubmit() {
        console.log(this.persons[0].firstName);
        console.log("123");
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
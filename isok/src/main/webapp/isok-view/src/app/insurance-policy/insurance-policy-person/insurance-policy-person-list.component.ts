import {Component, OnInit, Input} from '@angular/core';
import {NgModule} from '@angular/core';
import {InsurancePolicyService} from '../insurance-policy.service';
import {InsurancePolicyPersonRequest} from './insurance-policy-person-form.component';

@Component({
    selector: 'app-insurance-policy-person-list',
    templateUrl: './insurance-policy-person-list.component.html',
    styleUrls: ['./insurance-policy-person-list.component.css']
})
@NgModule({
    imports: [
    ],
    exports: [ // components that we want to make available
    ],
    declarations: [ // components for use in THIS module
    ],
    providers: [ // singleton services
        InsurancePolicyService
    ]
})
export class InsurancePolicyPersonListComponent {
    persons: InsurancePolicyPersonRequest[] = [];

    constructor(private insurancePolicyService: InsurancePolicyService) { }

    ngOnInit() {
        this.insurancePolicyService.todos.subscribe
            (updatedTodos => {
                this.persons = updatedTodos;
            });
    }
    
    delete(person:InsurancePolicyPersonRequest){
        this.insurancePolicyService.deletePerson(person);    
    }
    
    get(person:InsurancePolicyPersonRequest) {
        this.insurancePolicyService.setCurrentPerson(person);
    }
}
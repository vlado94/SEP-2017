import {Component, OnInit, Input, Output, EventEmitter} from '@angular/core';
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
    //persons: InsurancePolicyPersonRequest[] = [];
    @Input() persons;
    @Input() lista;
    @Output() onSelectForUpdate = new EventEmitter<InsurancePolicyPersonRequest>();

    constructor(private insurancePolicyService: InsurancePolicyService) { }

    ngOnInit() {

    }

    delete(person: InsurancePolicyPersonRequest) {
        let index = this.persons.indexOf(person);
        if (index != -1)
            this.persons.splice(index, 1);
    }

    get(person: InsurancePolicyPersonRequest) {
        this.onSelectForUpdate.emit(person);
    }

}
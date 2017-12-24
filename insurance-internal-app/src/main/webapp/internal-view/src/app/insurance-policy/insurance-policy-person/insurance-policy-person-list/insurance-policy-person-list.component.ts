import {Component, OnInit, Input, Output, EventEmitter} from '@angular/core';
import {NgModule} from '@angular/core';
import {InsurancePolicyService} from '../../insurance-policy.service';
import {InsurancePolicyPersonRequest} from '../insurance-policy-person-form/insurance-policy-person-form.component';

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

    @Input() persons;
    @Output() selectForUpdate = new EventEmitter<InsurancePolicyPersonRequest>();
    @Output() deletePerson = new EventEmitter<InsurancePolicyPersonRequest>();
    constructor(private insurancePolicyService: InsurancePolicyService) { }

    ngOnInit() {

    }

    delete(person: InsurancePolicyPersonRequest) {
        console.log("OBRISAN");
        this.persons[0];
        this.deletePerson.emit(person);
    }

    get(person: InsurancePolicyPersonRequest) {
        this.selectForUpdate.emit(person);
    }

}
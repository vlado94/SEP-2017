import {Component, Input, Output, EventEmitter, NgModule} from '@angular/core';
import {InsurancePolicyRequest} from '../insurance-policy-request';

import {InsurancePolicyPersonRequest} from '../insurance-policy-person/insurance-policy-person-form/insurance-policy-person-form.component';
import {InsurancePolicyService} from '../insurance-policy.service';
@Component({
    selector: 'app-insurance-policy-checkout',
    templateUrl: './insurance-policy-checkout.component.html',
    styleUrls: ['./insurance-policy-checkout.component.css']
})
@NgModule({
    imports: [
    ],
    exports: [ // components that we want to make available
    ],
    declarations: [ // components for use in THIS module
    ],
    providers: [ 
       InsurancePolicyService// singleton services
    ]
})
export class InsurancePolicyCheckoutComponent {

    @Output() nextTab = new EventEmitter<string>();
    @Input() persons;
    checkoutVar = null;
    changeTab(value: string) {
        this.nextTab.emit(value);
    }

      constructor(private insurancePolicyService: InsurancePolicyService) { }


    ngOnInit() {
    }


    @Input()
    set checkout(value){
        this.checkoutVar = value;
        console.log(value);
    }; 


}

import {Component, Input,Output,EventEmitter ,NgModule} from '@angular/core';
import {InsurancePolicyRequest} from '../insurance-policy-request';

import {InsurancePolicyPersonRequest} from '../insurance-policy-person/insurance-policy-person-form/insurance-policy-person-form.component';
import {InsurancePolicyCarRequest} from '../insurance-policy-car-form/insurance-policy-car-request';
import {InsurancePolicyHomeRequest} from '../insurance-policy-home-form/insurance-policy-home-request';

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
    providers: [ // singleton services
    ]
})
export class InsurancePolicyCheckoutComponent {

    @Output() nextTab = new EventEmitter<string>();
    @Input() insurancePrice;
    @Input() carInsurancePrice;
    @Input() homeInsurancePrice;
    changeTab(value: string) {
        this.nextTab.emit(value);
    }

      constructor() { }


    ngOnInit() {

    }




}

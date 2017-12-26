import {Component, Input, Output, EventEmitter, NgModule} from '@angular/core';
import {InsurancePolicyCar} from '../insurance-policy-car-form/insurance-policy-car-form.component';
import {InsurancePolicyHome} from '../insurance-policy-home-form/insurance-policy-home-form.component';

@Component({

    selector: 'app-insurance-policy-home-car',
    templateUrl: './insurance-policy-home-car.component.html',
    styleUrls: ['./insurance-policy-home-car.component.css']
})

@NgModule({
    imports: [],
    exports: [],
    declarations: [],
    providers: []

})

export class InsurancePolicyHomeCar {

    activeForm: string = null;
    @Input() insurancePolicyCar;
    @Input() insurancePolicyHome;

    @Output() insurancePolicyCarChanged = new EventEmitter<InsurancePolicyCar>();
    @Output() insurancePolicyHomeChanged = new EventEmitter<InsurancePolicyHome>();
    @Output() nextTab = new EventEmitter<string>();
    
    changeTab(value: string) {
        this.nextTab.emit(value);
    }
    
    showForm(value) {
        this.activeForm = value;
    }

    setInsurancePolicyCar(value) {
        this.insurancePolicyCarChanged.emit(value);
        if (value) {
            console.log("Dodavanje osigranja automobila...");
            console.log("Registaraska oznaka automobila: " + value.registrationNumber);
        }
    }

    setInsurancePolicyHome(value) {
        this.insurancePolicyHomeChanged.emit(value);
        if (value) {
            console.log("Dodavanje osigranja kuce...");
            console.log("Adresa kuce: " + value.address);
        }
    }
}
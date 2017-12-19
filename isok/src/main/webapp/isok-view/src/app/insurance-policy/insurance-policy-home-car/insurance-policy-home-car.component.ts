import {Component, Input, Output, EventEmitter, NgModule} from '@angular/core';
import {InsurancePolicyCar} from '../insurance-policy-car-form/insurance-policy-car-form.component';
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
    @Output() insurancePolicyCarChanged = new EventEmitter<InsurancePolicyCar>();

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
}
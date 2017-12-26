import {Component, Input, Output, EventEmitter, NgModule} from '@angular/core';
import {InsurancePolicyCarRequest} from '../insurance-policy-car-form/insurance-policy-car-request';
import {InsurancePolicyHomeRequest} from '../insurance-policy-home-form/insurance-policy-home-request';
import {InsurancePolicyCarCalculatePriceRequest} from '../insurance-policy-car-form/insurance-policy-car-calculate-price-request';
import {InsurancePolicyHomeCalculatePriceRequest} from '../insurance-policy-home-form/insurance-policy-home-calculate-price-request';

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
    @Input() carInsurancePrice;
    @Input() homeInsurancePrice;
    @Output() insurancePolicyCarChanged = new EventEmitter<InsurancePolicyCarRequest>();
    @Output() insurancePolicyHomeChanged = new EventEmitter<InsurancePolicyHomeRequest>();
    @Output() calculatePriceCar = new EventEmitter<InsurancePolicyCarCalculatePriceRequest>();
    @Output() calculatePriceHome = new EventEmitter<InsurancePolicyHomeCalculatePriceRequest>();



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

    calculatePriceCarInsurance(value) {
        this.calculatePriceCar.emit(value);
    }
    calculatePriceHomeInsurance(value) {
        this.calculatePriceHome.emit(value);
    }
}
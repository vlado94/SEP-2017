import {Component, Input, Output, EventEmitter} from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';

import { NgModule } from '@angular/core';
import {InsurancePolicyCarRequest} from './insurance-policy-car-request';
import {InsurancePolicyCarCalculatePriceRequest} from './insurance-policy-car-calculate-price-request';
import {InsurancePolicyService} from '../insurance-policy.service';

import { FactorService } from "../../factor/factor.service";
import { Factor } from '../../factor/factor';

@Component({
    selector: 'app-insurance-policy-car-form',
    templateUrl: './insurance-policy-car-form.component.html',
    styleUrls: ['./insurance-policy-car-form.component.css']
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

export class InsurancePolicyCarForm {
    insurancePolicyCarForm: FormGroup;
    @Output() setInsurancePolicyCar = new EventEmitter<InsurancePolicyCarRequest>();
    @Output() hideForm = new EventEmitter<string>();
    @Output() calculatePrice = new EventEmitter<InsurancePolicyCarCalculatePriceRequest>();
    @Input() price;

    paketi: Factor[];
    current: InsurancePolicyCarRequest = null;
    constructor(private insurancePolicyService: InsurancePolicyService, private factorService: FactorService) {
        this.insurancePolicyCarForm = new FormGroup({
            duration: new FormControl('', [
                Validators.required,
                Validators.pattern("[0-9]*")]),
            paket: new FormControl('', [
                Validators.required]),
            vehicle: new FormControl('', [Validators.required]),
            typeOfVehicle: new FormControl('', [Validators.required]),
            year: new FormControl('', [Validators.required, Validators.pattern("[0-9]{4}")]),
            registrationNumber: new FormControl('', [Validators.required]),
            chassisNumber: new FormControl('', [Validators.required]),
            firstName: new FormControl('', [Validators.required]),
            lastName: new FormControl('', [Validators.required]),
            jmbg: new FormControl('', [
                Validators.required,
                Validators.minLength(13),
                Validators.maxLength(13)
            ]),
        })
    }

    ngOnInit() {
        this.insurancePolicyCarForm.reset();
        this.insurancePolicyCarForm.controls['paket'].setValue('');
        this.insurancePolicyCarForm.controls['typeOfVehicle'].setValue('');

        this.insurancePolicyCarForm.valueChanges.subscribe(value => {
            console.log('Car form changes!!!!!!!!!!!!!!!!!!!!!!!', value)
            if (this.insurancePolicyCarForm.get('duration').valid && this.insurancePolicyCarForm.get('paket').valid) {
                let insurancePolicyCarCalculatePriceRequest: InsurancePolicyCarCalculatePriceRequest = new InsurancePolicyCarCalculatePriceRequest(value.duration, value.paket)
                this.calculatePrice.emit(insurancePolicyCarCalculatePriceRequest);
            } else {
                this.calculatePrice.emit(null);
            }
        })

        this.factorService.findByCategory(6)
            .subscribe(paketi => {
                this.paketi = paketi;
            })
    }

    @Input()
    set insurancePolicyCar(value: InsurancePolicyCarRequest) {
        this.current = value;
        console.log("SETTOVANJE POLISE ZA AUTO");
        if (value) {
            this.insurancePolicyCarForm.setValue({
                duration: value.duration,
                paket: value.paket,
                vehicle: value.vehicle,
                typeOfVehicle: value.typeOfVehicle,
                year: value.year,
                registrationNumber: value.registrationNumber,
                chassisNumber: value.chassisNumber,
                firstName: value.firstName,
                lastName: value.lastName,
                jmbg: value.jmbg
            })
        }
        else {
            this.insurancePolicyCarForm.reset();
            this.insurancePolicyCarForm.controls['paket'].setValue('');
            this.insurancePolicyCarForm.controls['typeOfVehicle'].setValue('');

        }
    }

    set(value) {
        var policyCar: InsurancePolicyCarRequest = null;
        if (value != null) {
            policyCar = new InsurancePolicyCarRequest(value.duration, value.paket, value.vehicle, value.typeOfVehicle, value.year, value.registrationNumber, value.chassisNumber, value.firstName, value.lastName, value.jmbg);
            this.setInsurancePolicyCar.emit(policyCar);
        } else {
            this.setInsurancePolicyCar.emit(null);
        }

        this.insurancePolicyCarForm.reset();
    }

    closeForm() {
        this.hideForm.emit(null);
    }
}
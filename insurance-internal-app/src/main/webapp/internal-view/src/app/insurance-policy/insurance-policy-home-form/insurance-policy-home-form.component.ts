import {Component, Input, Output, EventEmitter} from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';

import { NgModule } from '@angular/core';
import {InsurancePolicyHomeRequest} from './insurance-policy-home-request';
import {InsurancePolicyHomeCalculatePriceRequest} from './insurance-policy-home-calculate-price-request';
import {InsurancePolicyService} from '../insurance-policy.service';

import { FactorService } from "../../factor/factor.service";
import { Factor } from '../../factor/factor';

@Component({
    selector: 'app-insurance-policy-home-form',
    templateUrl: './insurance-policy-home-form.component.html',
    styleUrls: ['./insurance-policy-home-form.component.css']
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

export class InsurancePolicyHomeForm {
    insurancePolicyHomeForm: FormGroup;
    current: InsurancePolicyHomeRequest = null;

    @Output() setInsurancePolicyHome = new EventEmitter<InsurancePolicyHomeRequest>();

    @Output() hideForm = new EventEmitter<string>();
    @Output() calculatePrice = new EventEmitter<InsurancePolicyHomeCalculatePriceRequest>();
    @Input() price;
    sizes:Factor[];
    ages:Factor[];
    values:Factor[];
    risks:Factor[];
    constructor(private insurancePolicyService: InsurancePolicyService, private factorService: FactorService) {
        this.insurancePolicyHomeForm = new FormGroup({
            duration: new FormControl('', [
                Validators.required,
                Validators.pattern("[0-9]*")]),
            size: new FormControl('', [
                Validators.required]),
            age: new FormControl('', [
                Validators.required]),
            value: new FormControl('', [
                Validators.required]),
            risk: new FormControl('', [
                Validators.required]),
            address: new FormControl('', [
                Validators.required]),
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
        this.insurancePolicyHomeForm.reset();
            this.insurancePolicyHomeForm.controls['size'].setValue('');
            this.insurancePolicyHomeForm.controls['age'].setValue('');
            this.insurancePolicyHomeForm.controls['value'].setValue('');
            this.insurancePolicyHomeForm.controls['risk'].setValue('');
        
        this.insurancePolicyHomeForm.valueChanges.subscribe(value => {
            if (this.insurancePolicyHomeForm.get('duration').valid && this.insurancePolicyHomeForm.get('size').valid
                && this.insurancePolicyHomeForm.get('value').valid && this.insurancePolicyHomeForm.get('risk').valid
                && this.insurancePolicyHomeForm.get('age').valid) {
                let insurancePolicyHomeCalculatePriceRequest: InsurancePolicyHomeCalculatePriceRequest = new InsurancePolicyHomeCalculatePriceRequest(value.duration, value.size,
                    value.age, value.value, value.risk);
                this.calculatePrice.emit(insurancePolicyHomeCalculatePriceRequest);
            } else {
                this.calculatePrice.emit(null);
            }
        })
        
        this.factorService.findByCategory(7)
            .subscribe(sizes => {
                this.sizes = sizes;
            })

        this.factorService.findByCategory(8)
            .subscribe(ages => {
                this.ages = ages;
            }) 
        this.factorService.findByCategory(9)
            .subscribe(values => {
                this.values = values;
            })
        this.factorService.findByCategory(10)
            .subscribe(risks => {
                this.risks = risks;
            })                       
    }

    @Input()
    set insurancePolicyHome(value: InsurancePolicyHomeRequest) {
        this.current = value;
        console.log("SETTOVANJE POLISE ZA KUCU");
        if (value) {
            this.insurancePolicyHomeForm.setValue({
                duration: value.duration,
                size: value.size,
                age: value.age,
                risk: value.risk,
                value: value.value,
                address: value.address,
                firstName: value.firstName,
                lastName: value.lastName,
                jmbg: value.jmbg
            })
        }
        else {
            this.insurancePolicyHomeForm.reset();
            this.insurancePolicyHomeForm.controls['size'].setValue('');
            this.insurancePolicyHomeForm.controls['age'].setValue('');
            this.insurancePolicyHomeForm.controls['value'].setValue('');
            this.insurancePolicyHomeForm.controls['risk'].setValue('');
        }
    }

    set(value) {
        var policyHome: InsurancePolicyHomeRequest = null;
        if (value != null) {
            policyHome = new InsurancePolicyHomeRequest(value.duration, value.size, value.age, value.value, value.risk, value.address, value.firstName, value.lastName, value.jmbg);
            this.setInsurancePolicyHome.emit(policyHome);
        } else {
            this.setInsurancePolicyHome.emit(null);
        }

        this.insurancePolicyHomeForm.reset();
    }

    closeForm() {
        this.hideForm.emit(null);
    }
}
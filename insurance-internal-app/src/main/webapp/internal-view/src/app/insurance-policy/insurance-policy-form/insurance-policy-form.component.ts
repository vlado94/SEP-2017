import {Component, OnInit, Input, Output, EventEmitter} from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {Router} from '@angular/router';
import { NgModule } from '@angular/core';
import {InsurancePolicyService} from '../insurance-policy.service';
import {InsurancePolicyPersonRequest} from '../insurance-policy-person/insurance-policy-person-form/insurance-policy-person-form.component';
import {InsurancePolicyRequest} from '../insurance-policy-request';
import {InsurancePolicyCalculatePriceRequest} from '../insurance-policy-calculate-price-request';

import { FactorService } from "../../factor/factor.service";
import { Factor } from '../../factor/factor';

function dateValidator(date: FormControl) {
    let input = date.value;
    let parts = input.split('-');

    let inpuDate = new Date(+parts[0], +parts[1] - 1, parts[2], 23, 59);
    let currentDate = new Date();
    if (inpuDate < currentDate)
        return {
            dateValidator: {
                valid: false
            }
        }
    else
        return null;
}

function numberOfPersonsValidator(num: FormControl) {
    if ((+num.value) <= 0)
        return {
            numberOfPersonsValidator: {
                valid: false
            }
        }
    else
        return null;

}
@Component({
    selector: 'app-insurance-policy-form',
    templateUrl: './insurance-policy-form.component.html',
    styleUrls: ['./insurance-policy-form.component.css'],

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
export class InsurancePolicyFormComponent {

    insurancePolicy: FormGroup;
    contractorAdded: boolean = false;
    request: InsurancePolicyRequest;
    public addPersonButton: boolean = true;
    @Output() onFormSubmit = new EventEmitter<InsurancePolicyRequest>();
    @Output() nextTab = new EventEmitter<string>();
    @Output() calculatePrice = new EventEmitter<InsurancePolicyCalculatePriceRequest>();
    @Input() price;

    //price: string = null;
    regions: Factor[];
    sports: Factor[];
    agesCategory: Factor[];
    amounts: Factor[];
    types: Factor[];
    
    calculatePriceRequest: InsurancePolicyCalculatePriceRequest = null;
    constructor(private insurancePolicyService: InsurancePolicyService, private factorService: FactorService) { }


    ngOnInit() {
        this.insurancePolicy = new FormGroup({
            startDate: new FormControl('', {
                //updateOn: 'blur',
                validators:[dateValidator]
            }),
            duration: new FormControl('',{

                //updateOn: 'blur',
                validators: [Validators.required,Validators.pattern("[0-9]*"),numberOfPersonsValidator]
            }),
            typeOfPolicy: new FormControl('',{
                //updateOn: 'blur',
                validators: [Validators.required]
            }),
            age: new FormControl('',{
                //updateOn: 'blur'    
            }),
            numberOfPersonsUpTo16: new FormControl('',{
                //updateOn: 'blur'
            }),
            numberOfPersonsBetween16And60: new FormControl('',{
               //updateOn: 'blur'
            }),
            numberOfPersonsOver60: new FormControl('',{
                //updateOn: 'blur'
            }),
            region: new FormControl('',{
               //updateOn: 'blur',
               validators:[ Validators.required]
            }),
            sport: new FormControl('',{
               //updateOn: 'blur'
           }),
            amount: new FormControl('',{
               //updateOn: 'blur',
               validators:[Validators.required]
           }),
        });

        this.factorService.findByCategory(3)
            .subscribe(regions => {
                this.regions = regions;
            })

        this.factorService.findByCategory(1)
            .subscribe(sports => {
                this.sports = sports;
            })

        this.factorService.findByCategory(2)
            .subscribe(agesCategory => {
                this.agesCategory = agesCategory;
            })

        this.factorService.findByCategory(4)
            .subscribe(types => {
                this.types = types;
            })

        this.factorService.findByCategory(5)
            .subscribe(amounts => {
                this.amounts = amounts;
            })
        this.insurancePolicy
            .valueChanges
            .debounceTime(1000) // wait 300ms after the last event before emitting last event
            .distinctUntilChanged().subscribe(value => {
            console.log('Form changes', value)
            if (this.insurancePolicy.valid && this.checkNumberOfPeople()) {
                this.calculatePriceRequest = new InsurancePolicyCalculatePriceRequest(value.startDate, value.duration,
                    value.region, value.sport, value.amount, value.typeOfPolicy, +value.numberOfPersonsUpTo16, +value.numberOfPersonsBetween16And60, +value.numberOfPersonsOver60)
                this.calculatePrice.emit(this.calculatePriceRequest);
                /*this.insurancePolicyService.calculateSuggestedPrice(insurancePolicyCalculatePriceRequest).subscribe(price => {
                    this.price = price;
                })*/
            } else {
                this.calculatePriceRequest = null;
                this.calculatePrice.emit(null);
            }
        })

    }
    onSubmit({value}: { value }) {
        let insurancePolicyRequest = new InsurancePolicyRequest(value.startDate, value.duration,
            value.region, value.sport, value.amount, value.typeOfPolicy, +value.numberOfPersons, +value.numberOfPersonsUpTo16, +value.numberOfPersonsBetween16And60, +value.numberOfPersonsOver60)
        this.onFormSubmit.emit(insurancePolicyRequest);
        console.log('Submitting first page...');
        this.nextTab.emit('2');
    }

    checkNumberOfPeople() {
        let result = false;
        let upTo16: number = +this.insurancePolicy.get('numberOfPersonsUpTo16').value;
        let between16And60: number = +this.insurancePolicy.get('numberOfPersonsBetween16And60').value;
        let over60: number = +this.insurancePolicy.get('numberOfPersonsOver60').value;
        let sum: number = upTo16 + between16And60 + over60;

        //let numOfPersons = +this.insurancePolicy.get('numberOfPersons').value;
        if (sum > 0) {
            result = true;
            //console.log("Valid");
        }
        /*if (result)
            console.log("Valid");

        else
            console.log("Invalid");*/

        return result;
    }

    convertDate(d) {
        let parts = d.split('-');
        return new Date(+parts[0], +parts[1] - 1, parts[2]);
 }
}

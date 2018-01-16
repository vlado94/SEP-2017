import {Component, Input, Output, EventEmitter} from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import { InsurancePolicyHome } from '../policy-home';
import { Factor } from '../../../factor/factor';
import { FactorService } from "../../../factor/factor.service";



@Component({
	selector: 'app-insurance-policy-home',
	templateUrl: './insurance-policy-home.component.html',
	styleUrls: ['./insurance-policy-home.component.scss']
})
export class InsurancePolicyHomeComponent {

	insurancePolicyHomeForm: FormGroup;

	@Output() setInsurancePolicyHome = new EventEmitter<InsurancePolicyHome>();

	@Output() hideForm = new EventEmitter<string>();
    currentHome: InsurancePolicyHome = null;
	//@Output() calculatePrice = new EventEmitter<InsurancePolicyHomeCalculatePriceRequest>();
	//@Input() price;
    sizes: Factor[];
    ages: Factor[];
    values: Factor[];
    risks: Factor[];

    constructor(private factorService: FactorService) { 
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
            personNo: new FormControl('', [
                Validators.required,
                Validators.minLength(13),
                Validators.maxLength(13)
                ]),
        })

        this.factorService.findByCategory(6)
            .subscribe(sizes => {
                this.sizes = sizes;
            })

        this.factorService.findByCategory(7)
            .subscribe(ages => {
                this.ages = ages;
            })
        this.factorService.findByCategory(8)
            .subscribe(values => {
                this.values = values;
            })
        this.factorService.findByCategory(9)
            .subscribe(risks => {
                this.risks = risks;
            })
    }



    @Input()
    set insurancePolicyHome(value: InsurancePolicyHome) {
        this.currentHome = value;
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
                personNo: value.personNo
            })
        }
        else {
            this.insurancePolicyHomeForm.reset();
        }
    }

    submitHome() {

        var policyHome: InsurancePolicyHome = null;
        var value = this.insurancePolicyHomeForm.value;
        
        policyHome = new InsurancePolicyHome(value.duration, value.size, value.age, value.value, value.risk, value.address, value.firstName, value.lastName, value.personNo);
        this.setInsurancePolicyHome.emit(policyHome);
        
        this.hideForm.emit(null);
        //this.insurancePolicyHomeForm.reset();
    }

    cancelHome(){
        console.log("cancel home");
        this.setInsurancePolicyHome.emit(null);
        this.hideForm.emit(null);
    }

    closeForm() {
        this.hideForm.emit(null);
    }

}

import { BrowserModule } from '@angular/platform-browser';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { NgModule } from '@angular/core';
import { HttpModule } from '@angular/http';


import { AppComponent } from './app.component';
import { AppRoutingModule } from './/app-routing.module';


import { InsurancePolicyComponent} from './insurance-policy/insurance-policy.component';
import { InsurancePolicyFormComponent} from './insurance-policy/insurance-policy-form/insurance-policy-form.component';
import {InsurancePolicyPersonComponent} from './insurance-policy/insurance-policy-person/insurance-policy-person.component';
import {InsurancePolicyPersonFormComponent} from './insurance-policy/insurance-policy-person/insurance-policy-person-form/insurance-policy-person-form.component';
import {InsurancePolicyPersonListComponent} from './insurance-policy/insurance-policy-person/insurance-policy-person-list/insurance-policy-person-list.component';
import {InsurancePolicyHomeCar} from './insurance-policy/insurance-policy-home-car/insurance-policy-home-car.component';
import {InsurancePolicyHomeForm} from './insurance-policy/insurance-policy-home-form/insurance-policy-home-form.component';
import {InsurancePolicyCarForm} from './insurance-policy/insurance-policy-car-form/insurance-policy-car-form.component';
import {InsurancePolicyCheckoutComponent} from './insurance-policy/insurance-policy-checkout/insurance-policy-checkout.component';
import {InsurancePolicyService} from './insurance-policy/insurance-policy.service';


import { FactorService } from "./factor/factor.service";


@NgModule({
    declarations: [
        AppComponent,

        InsurancePolicyComponent,
        InsurancePolicyFormComponent,
        InsurancePolicyPersonComponent,
        InsurancePolicyPersonFormComponent,
        InsurancePolicyPersonListComponent,
        InsurancePolicyHomeCar,
        InsurancePolicyHomeForm,
        InsurancePolicyCarForm,
        InsurancePolicyCheckoutComponent
    ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        ReactiveFormsModule,
        FormsModule,
        HttpModule

    ],
    providers: [FactorService, InsurancePolicyService],
    bootstrap: [AppComponent]
})
export class AppModule { }

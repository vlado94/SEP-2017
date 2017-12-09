import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { HttpModule } from '@angular/http';

import { AppComponent } from './app.component';
import { AppRoutingModule } from './/app-routing.module';

import { factorsRouting } from "./factor/factor.routing";
import { FactorComponent } from './factor/factor.component';
import { FactorListComponent } from './factor/factor-list/factor-list.component';
import { FactorFormComponent } from './factor/factor-form/factor-form.component';
import { FactorService } from "./factor/factor.service";

import { InsurancePolicyComponent} from './insurance-policy/insurance-policy.component';
import { InsurancePolicyFormComponent} from './insurance-policy/insurance-policy-form/insurance-policy-form.component';
import {InsurancePolicyPersonComponent} from './insurance-policy/insurance-policy-person/insurance-policy-person.component';
import {InsurancePolicyPersonFormComponent} from './insurance-policy/insurance-policy-person/insurance-policy-person-form/insurance-policy-person-form.component';
import {InsurancePolicyPersonListComponent} from './insurance-policy/insurance-policy-person/insurance-policy-person-list/insurance-policy-person-list.component';

import {InsurancePolicyService} from './insurance-policy/insurance-policy.service';
import { KeycloakService } from './keycloak/service/keycloak.service';
import { KEYCLOAK_HTTP_PROVIDER } from './keycloak/service/keycloak.http';


import { CategoryFactorService } from "./category-factor/category-factor.service";
import { categoryFactorsRouting } from "./category-factor/category-factor.routing";
import { CategoryFactorComponent } from './category-factor/category-factor.component';
import { CategoryFactorListComponent } from './category-factor/category-factor-list/category-factor-list.component';
import { CategoryFactorFormComponent } from './category-factor/category-factor-form/category-factor-form.component';

import { PriceListComponent } from './price-list/price-list.component';
import { priceListRouting } from "./price-list/price-list.routing";
import { PriceService } from "./price-list/price.service";

@NgModule({
    declarations: [
        AppComponent,

        CategoryFactorComponent,
        CategoryFactorListComponent,
        CategoryFactorFormComponent,

        FactorComponent,
        FactorListComponent,
        FactorFormComponent,

        PriceListComponent,

        InsurancePolicyComponent,
        InsurancePolicyFormComponent,
        InsurancePolicyPersonComponent,
        InsurancePolicyPersonFormComponent,
        InsurancePolicyPersonListComponent
    ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        factorsRouting,
        categoryFactorsRouting,
        priceListRouting,
        ReactiveFormsModule,
        FormsModule,
        HttpModule
    ],
    providers: [CategoryFactorService, FactorService, PriceService, InsurancePolicyService, KEYCLOAK_HTTP_PROVIDER, KeycloakService],
    bootstrap: [AppComponent]
})
export class AppModule { }

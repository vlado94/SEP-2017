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

import { InsurancePolicyComponent} from './insurance-policy/insurance-policy.component';
import { InsurancePolicyFormComponent} from './insurance-policy/insurance-policy-form/insurance-policy-form.component';
import {InsurancePolicyPersonFormComponent} from './insurance-policy/insurance-policy-person/insurance-policy-person-form.component';
import {InsurancePolicyPersonListComponent} from './insurance-policy/insurance-policy-person/insurance-policy-person-list.component';

import {InsurancePolicyService} from './insurance-policy/insurance-policy.service';


import { FactorService } from "./factor/factor.service";
import { CategoryFactorService } from "./category-factor/category-factor.service";
import { categoryFactorsRouting } from "./category-factor/category-factor.routing";
import { CategoryFactorComponent } from './category-factor/category-factor.component';
import { CategoryFactorListComponent } from './category-factor/category-factor-list/category-factor-list.component';
import { CategoryFactorFormComponent } from './category-factor/category-factor-form/category-factor-form.component';

@NgModule({
  declarations: [ 
    AppComponent,

    CategoryFactorComponent,
    CategoryFactorListComponent,
    CategoryFactorFormComponent, 

    FactorComponent,
    FactorListComponent,
    FactorFormComponent,
    InsurancePolicyComponent,
    InsurancePolicyFormComponent,
    InsurancePolicyPersonFormComponent,
    InsurancePolicyPersonListComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    factorsRouting,
    categoryFactorsRouting,
    ReactiveFormsModule,
    FormsModule,
    HttpModule
  ],
  providers: [CategoryFactorService,FactorService,InsurancePolicyService],
  bootstrap: [AppComponent]
})
export class AppModule { }

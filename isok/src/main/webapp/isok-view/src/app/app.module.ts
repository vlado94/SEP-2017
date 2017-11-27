import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { HttpModule } from '@angular/http';


import { AppComponent } from './app.component';
import { CategoryFactorComponent } from './category-factor/category-factor.component';
import { AppRoutingModule } from './/app-routing.module';
import { factorsRouting } from "./factor/factor.routing";
import { FactorComponent } from './factor/factor.component';
import { CategoryFactorListComponent } from './category-factor/category-factor-list/category-factor-list.component';
import { FactorListComponent } from './factor/factor-list/factor-list.component';
import { FactorFormComponent } from './factor/factor-form/factor-form.component';
import { InsurancePolicyComponent} from './insurance-policy/insurance-policy.component';
import { InsurancePolicyFormComponent} from './insurance-policy/insurance-policy-form/insurance-policy-form.component';

@NgModule({
  declarations: [
    AppComponent,
    CategoryFactorComponent,
    CategoryFactorListComponent,
    FactorComponent,
    FactorListComponent,
    FactorFormComponent,
    InsurancePolicyComponent,
    InsurancePolicyFormComponent  
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    factorsRouting,
    ReactiveFormsModule,
    FormsModule,
    HttpModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }

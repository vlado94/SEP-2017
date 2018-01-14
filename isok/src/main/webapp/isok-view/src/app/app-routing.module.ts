import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { CategoryFactorComponent } from './category-factor/category-factor.component';
import {InsurancePolicyComponent} from './insurance-policy/insurance-policy.component';
import { RulesComponent } from './rules/rules.component';
import { CommonModule } from '@angular/common';
import { ResolverPriceManagement } from './resolver/resolver-price-management'

const routes: Routes = [
    { path: 'insurancePolicy', component: InsurancePolicyComponent },
    {
      path: 'rules', component : RulesComponent,
      resolve: {
  		resolver : ResolverPriceManagement
  	}
    }
];


@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutingModule { }

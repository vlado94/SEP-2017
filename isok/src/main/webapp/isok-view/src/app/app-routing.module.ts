import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { CategoryFactorComponent } from './category-factor/category-factor.component';
import {InsurancePolicyComponent} from './insurance-policy/insurance-policy.component';
import { RulesComponent } from './rules/rules.component';
import { CommonModule } from '@angular/common';

const routes: Routes = [
    { path: 'insurancePolicy', component: InsurancePolicyComponent },
    {
      path: 'rules', component : RulesComponent
    }
];


@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutingModule { }

import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { InsurancePolicyComponent } from './insurance-policy/insurance-policy.component';
import { CommonModule } from '@angular/common';
import { Resolver } from './resolver/user-resolver';

const routes: Routes = [
    {
        path: '',
        redirectTo: 'insurancePolicy',
        pathMatch: 'full'
    },
    { path: 'insurancePolicy',
      component: InsurancePolicyComponent,
      resolve: {
  		resolver : Resolver
  	  }
    }

];


@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutingModule { }

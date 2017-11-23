import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { CategoryFactorComponent } from './category-factor/category-factor.component';

import { CommonModule } from '@angular/common';

const routes: Routes = [
  { path: 'categoryFactor', component: CategoryFactorComponent },
];

@NgModule({
  imports: [ RouterModule.forRoot(routes) ],
  exports: [ RouterModule ]
})
export class AppRoutingModule { }

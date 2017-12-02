import { Routes, RouterModule } from '@angular/router';

import { CategoryFactorComponent } from './category-factor.component';

const categoryFactorsRouts: Routes = [
  { path: 'categoryFactors', component: CategoryFactorComponent, pathMatch: 'full' },
  { path: 'categoryFactors/:id', component: CategoryFactorComponent},
  { path: 'categoryFactors/findFactorsByID/:id', component: CategoryFactorComponent},
  { path: 'categoryFactors/findFactorsByID?:id', component: CategoryFactorComponent},
  { path: 'categoryFactors/:id', component: CategoryFactorComponent},
];

export const categoryFactorsRouting = RouterModule.forChild(categoryFactorsRouts);

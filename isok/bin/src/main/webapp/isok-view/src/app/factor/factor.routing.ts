import { Routes, RouterModule } from '@angular/router';

import { FactorComponent } from './factor.component';

const factorsRouts: Routes = [
  { path: 'factors', component: FactorComponent, pathMatch: 'full' },
  { path: 'factors/:id', component: FactorComponent}
];

export const factorsRouting = RouterModule.forChild(factorsRouts);

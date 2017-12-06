import { Routes, RouterModule } from '@angular/router';

import { PriceListComponent } from './price-list.component';

const priceListRouts: Routes = [
  { path: 'priceList', component: PriceListComponent, pathMatch: 'full' },
  { path: 'priceList/:id', component: PriceListComponent}
];

export const priceListRouting = RouterModule.forChild(priceListRouts);

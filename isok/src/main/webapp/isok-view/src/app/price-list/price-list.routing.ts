import { Routes, RouterModule } from '@angular/router';

import { PriceListComponent } from './price-list.component';
import { Resolver } from '../resolver/userResolver'

const priceListRouts: Routes = [
  { path: 'priceList', component: PriceListComponent, pathMatch: 'full',
    resolve: {
  		resolver : Resolver
  	}
  },
  { path: 'priceList/:id', component: PriceListComponent,
  	resolve: {
  		resolver : Resolver
  	}
  }
];

export const priceListRouting = RouterModule.forChild(priceListRouts);

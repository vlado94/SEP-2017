import { Routes, RouterModule } from '@angular/router';

import { FactorComponent } from './factor.component';
import { Resolver } from '../resolver/userResolver'

const factorsRouts: Routes = [
  { path: 'factors', component: FactorComponent, pathMatch: 'full',
  	resolve: {
  		resolver : Resolver
  	}
  },
  { path: 'factors/:id', component: FactorComponent,
  	resolve: {
  		resolver : Resolver
  	}
  }
];

export const factorsRouting = RouterModule.forChild(factorsRouts);

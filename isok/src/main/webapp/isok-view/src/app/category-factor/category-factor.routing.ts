import { Routes, RouterModule } from '@angular/router';
import { environment } from '../../environments/environment';

import { CategoryFactorComponent } from './category-factor.component';
import { Resolver } from '../resolver/userResolver'

const categoryFactorsRouts: Routes = [
  	{ path: 'categoryFactors', component: CategoryFactorComponent, 
  	pathMatch: 'full',
  	resolve: {
  		resolver : Resolver
  	}
  	},
  	{ path: 'categoryFactors/:id', component: CategoryFactorComponent,
  	  resolve: {
  		resolver : Resolver
  	}
  	},
 	{ path: 'categoryFactors/findFactorsByID/:id', component: CategoryFactorComponent,
 		resolve: {
  		resolver : Resolver
  	}
 	},
  	{ path: 'categoryFactors/findFactorsByID?:id', component: CategoryFactorComponent,
  	  resolve: {
  		resolver : Resolver
  	}
  	},
  	{ path: 'categoryFactors/:id', component: CategoryFactorComponent,
  	  resolve: {
  		resolver : Resolver
  	}
  	},
];

export const categoryFactorsRouting = RouterModule.forChild(categoryFactorsRouts);

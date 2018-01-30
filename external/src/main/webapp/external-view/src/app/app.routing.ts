import { NgModule } from '@angular/core'
import { CommonModule, } from '@angular/common';
import { BrowserModule  } from'@angular/platform-browser';
import { Routes, RouterModule } from '@angular/router';

import { UserProfileComponent } from './user-profile/user-profile.component';
import { InsurancePolicyComponent } from './insurance-policy/insurance-policy.component';
import { HomePageComponent } from './home-page/home-page.component';
import { AboutComponent } from "./about/about.component";
import { PaypalExecuteComponent } from './paypal-execute/paypal-execute.component';
import { CardPaymentComponent } from './card-payment/card-payment.component';

const routes: Routes =[
    { path: 'user-profile',   component: UserProfileComponent },
    { path: 'policy',   component: InsurancePolicyComponent },
    { path: '',  component: HomePageComponent},
    { path: 'about', component : AboutComponent},
    { path: 'paypal', component :  PaypalExecuteComponent },
    { path: 'cardPayment', component : CardPaymentComponent}
];

@NgModule({
  imports: [
    CommonModule,
    BrowserModule,
    RouterModule.forRoot(routes)
  ],
  exports: [
  ],
})
export class AppRoutingModule { }

import { NgModule } from '@angular/core'
import { CommonModule, } from '@angular/common';
import { BrowserModule  } from'@angular/platform-browser';
import { Routes, RouterModule } from '@angular/router';

import { UserProfileComponent } from './user-profile/user-profile.component';
import { InsurancePolicyComponent } from './insurance-policy/insurance-policy.component';
import { HomePageComponent } from './home-page/home-page.component';



const routes: Routes =[
    { path: 'user-profile',   component: UserProfileComponent },
    { path: 'policy',   component: InsurancePolicyComponent },
    { path: '',  component: HomePageComponent}
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

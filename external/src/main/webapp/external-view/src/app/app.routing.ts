import { NgModule } from '@angular/core'
import { CommonModule, } from '@angular/common';
import { BrowserModule  } from'@angular/platform-browser';
import { Routes, RouterModule } from '@angular/router';

import { UserProfileComponent } from './user-profile/user-profile.component';
import { PolicyComponent } from './policy/policy.component';
import { ParentPolicyComponent } from './parent-policy/parent-policy.component';
import { HomePageComponent } from './home-page/home-page.component';
import { UserDataComponent } from './user-data/user-data.component';
import { CarComponent } from './car/car.component';


const routes: Routes =[
    { path: 'user-profile',   component: UserProfileComponent },
    { path: 'policy',   component: PolicyComponent },
    { path: 'parentPolicy', component: ParentPolicyComponent},
    { path: 'user-data', component: UserDataComponent},
    { path: 'car', component: CarComponent},
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

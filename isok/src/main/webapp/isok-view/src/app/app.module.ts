import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { HttpModule } from '@angular/http';


import { AppComponent } from './app.component';
import { CategoryFactorComponent } from './category-factor/category-factor.component';
import { AppRoutingModule } from './/app-routing.module';
import { CategoryFactorListComponent } from './category-factor/category-factor-list/category-factor-list.component';


@NgModule({
  declarations: [
    AppComponent,
    CategoryFactorComponent,
    CategoryFactorListComponent,
    
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    FormsModule,
    HttpModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }

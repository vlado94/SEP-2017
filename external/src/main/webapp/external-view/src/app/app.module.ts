import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule  } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { RouterModule } from '@angular/router';

import { AppRoutingModule } from './app.routing';
import { ComponentsModule } from './components/components.module';

import { AppComponent } from './app.component';

/*import { DashboardComponent } from './dashboard/dashboard.component';*/
import { UserProfileComponent } from './user-profile/user-profile.component';
/*import { TableListComponent } from './table-list/table-list.component';
import { TypographyComponent } from './typography/typography.component';
import { IconsComponent } from './icons/icons.component';
import { MapsComponent } from './maps/maps.component';
import { NotificationsComponent } from './notifications/notifications.component';
import { UpgradeComponent } from './upgrade/upgrade.component';*/
import { BsDatepickerModule } from 'ngx-bootstrap/datepicker';
import { PolicyComponent } from './policy/policy.component';
import { ParentPolicyComponent } from './parent-policy/parent-policy.component';
import { UserDataComponent } from './user-data/user-data.component';
import { HeaderCompComponent } from './header-comp/header-comp.component';
import { HomePageComponent } from './home-page/home-page.component';
import { FooterCompComponent } from './footer-comp/footer-comp.component';
import { UserDataService } from './user-data/user-data.service';
import { CarComponent } from './car/car.component';
import { HouseComponent } from './house/house.component';
import { InsurancePolicyComponent } from './insurance-policy/insurance-policy.component';
import { InsurancePolicyFormComponent } from './insurance-policy/insurance-policy-form/insurance-policy-form.component';
import { InsurancePolicyPersonComponent } from './insurance-policy/insurance-policy-person/insurance-policy-person.component';
import { InsurancePolicyPersonFormComponent } from './insurance-policy/insurance-policy-person/insurance-policy-person-form/insurance-policy-person-form.component';
import { InsurancePolicyPersonTableComponent } from './insurance-policy/insurance-policy-person/insurance-policy-person-table/insurance-policy-person-table.component';

@NgModule({
  declarations: [
    AppComponent,
    UserProfileComponent,
    PolicyComponent,
    ParentPolicyComponent,
    UserDataComponent,
    HeaderCompComponent,
    HomePageComponent,
    FooterCompComponent,
    CarComponent,
    HouseComponent,
    InsurancePolicyComponent,
    InsurancePolicyFormComponent,
    InsurancePolicyPersonComponent,
    InsurancePolicyPersonFormComponent,
    InsurancePolicyPersonTableComponent

  ],
  imports: [BsDatepickerModule.forRoot(),
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    HttpModule,
    ComponentsModule,
RouterModule,
    AppRoutingModule
  ],
  providers: [UserDataService],
  bootstrap: [AppComponent]
})
export class AppModule { }

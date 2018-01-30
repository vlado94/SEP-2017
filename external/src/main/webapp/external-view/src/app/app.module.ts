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

import { HeaderCompComponent } from './header-comp/header-comp.component';
import { HomePageComponent } from './home-page/home-page.component';
import { FooterCompComponent } from './footer-comp/footer-comp.component';

import { InsurancePolicyComponent } from './insurance-policy/insurance-policy.component';
import { InsurancePolicyFormComponent } from './insurance-policy/insurance-policy-form/insurance-policy-form.component';
import { InsurancePolicyPersonComponent } from './insurance-policy/insurance-policy-person/insurance-policy-person.component';
import { InsurancePolicyPersonFormComponent } from './insurance-policy/insurance-policy-person/insurance-policy-person-form/insurance-policy-person-form.component';
import { InsurancePolicyPersonTableComponent } from './insurance-policy/insurance-policy-person/insurance-policy-person-table/insurance-policy-person-table.component';
import { InsurancePolicyHomeCarComponent } from './insurance-policy/insurance-policy-home-car/insurance-policy-home-car.component';
import { InsurancePolicyCarComponent } from './insurance-policy/insurance-policy-home-car/insurance-policy-car/insurance-policy-car.component';
import { InsurancePolicyHomeComponent } from './insurance-policy/insurance-policy-home-car/insurance-policy-home/insurance-policy-home.component';
import { FactorService } from './factor/factor.service';
import { InsurancePolicyService } from './insurance-policy/insurance-policy.service';
import { InsurancePolicyCheckoutComponent } from './insurance-policy/insurance-policy-checkout/insurance-policy-checkout.component';
import { PaypalService } from './insurance-policy/insurance-policy-checkout/paypal.service';
import { AboutComponent } from './about/about.component';
import { PaypalExecuteComponent } from './paypal-execute/paypal-execute.component';
import { PaypalExecuteServiceService } from './paypal-execute/paypal-execute-service.service';
import { CardPaymentComponent } from './card-payment/card-payment.component';

@NgModule({
  declarations: [
    AppComponent,
    UserProfileComponent,
    HeaderCompComponent,
    HomePageComponent,
    FooterCompComponent,
    InsurancePolicyComponent,
    InsurancePolicyFormComponent,
    InsurancePolicyPersonComponent,
    InsurancePolicyPersonFormComponent,
    InsurancePolicyPersonTableComponent,
    InsurancePolicyHomeCarComponent,
    InsurancePolicyCarComponent,
    InsurancePolicyHomeComponent,
    InsurancePolicyCheckoutComponent,
    AboutComponent,
    PaypalExecuteComponent,
    CardPaymentComponent

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
  providers: [FactorService, InsurancePolicyService, PaypalService, PaypalExecuteServiceService],
  bootstrap: [AppComponent]
})
export class AppModule { }

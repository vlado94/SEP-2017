import { Component, OnInit } from '@angular/core';
import { Contract } from './keycloak/model/contract.model';
import { User } from './keycloak/model/user.model';
import { KeycloakService } from './keycloak/service/keycloak.service';
import { InsurancePolicyService } from './insurance-policy/insurance-policy.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {

  	profile: User;
    title = 'app';

    constructor(private keycloakService: KeycloakService,private insurancePolicyService: InsurancePolicyService) {
    }

    public ngOnInit(): void {
      this.profile = this.keycloakService.getUser();
      console.log(this.profile);
    }

    public isPriceManagement(): boolean {
      //console.log("keycloak " + JSON.stringify(KeycloakService.auth.authz));
      return this.keycloakService.hasRole('price_management');
    }

    public isSeller(): boolean {
      return this.keycloakService.hasRole('seller');
    }

    public logout() {
      this.keycloakService.logout();
    }

    public mail() {
      this.keycloakService.mail();
    }

    public internal() {
      window.location.href= "http://localhost:4500";
    }

}

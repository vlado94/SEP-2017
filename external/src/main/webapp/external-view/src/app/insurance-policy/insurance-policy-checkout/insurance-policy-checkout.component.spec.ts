import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { InsurancePolicyCheckoutComponent } from './insurance-policy-checkout.component';

describe('InsurancePolicyCheckoutComponent', () => {
  let component: InsurancePolicyCheckoutComponent;
  let fixture: ComponentFixture<InsurancePolicyCheckoutComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ InsurancePolicyCheckoutComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(InsurancePolicyCheckoutComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { InsurancePolicyPinComponent } from './insurance-policy-pin.component';

describe('InsurancePolicyPinComponent', () => {
  let component: InsurancePolicyPinComponent;
  let fixture: ComponentFixture<InsurancePolicyPinComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ InsurancePolicyPinComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(InsurancePolicyPinComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

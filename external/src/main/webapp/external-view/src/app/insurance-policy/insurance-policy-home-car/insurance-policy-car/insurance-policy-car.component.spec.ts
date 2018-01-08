import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { InsurancePolicyCarComponent } from './insurance-policy-car.component';

describe('InsurancePolicyCarComponent', () => {
  let component: InsurancePolicyCarComponent;
  let fixture: ComponentFixture<InsurancePolicyCarComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ InsurancePolicyCarComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(InsurancePolicyCarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { InsurancePolicyHomeCarComponent } from './insurance-policy-home-car.component';

describe('InsurancePolicyHomeCarComponent', () => {
  let component: InsurancePolicyHomeCarComponent;
  let fixture: ComponentFixture<InsurancePolicyHomeCarComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ InsurancePolicyHomeCarComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(InsurancePolicyHomeCarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

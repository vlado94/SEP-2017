import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { InsurancePolicyFormComponent } from './insurance-policy-form.component';

describe('InsurancePolicyFormComponent', () => {
  let component: InsurancePolicyFormComponent;
  let fixture: ComponentFixture<InsurancePolicyFormComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ InsurancePolicyFormComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(InsurancePolicyFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

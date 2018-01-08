import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { InsurancePolicyPersonFormComponent } from './insurance-policy-person-form.component';

describe('InsurancePolicyPersonFormComponent', () => {
  let component: InsurancePolicyPersonFormComponent;
  let fixture: ComponentFixture<InsurancePolicyPersonFormComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ InsurancePolicyPersonFormComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(InsurancePolicyPersonFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { InsurancePolicyHomeComponent } from './insurance-policy-home.component';

describe('InsurancePolicyHomeComponent', () => {
  let component: InsurancePolicyHomeComponent;
  let fixture: ComponentFixture<InsurancePolicyHomeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ InsurancePolicyHomeComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(InsurancePolicyHomeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { InsurancePolicyPersonTableComponent } from './insurance-policy-person-table.component';

describe('InsurancePolicyPersonTableComponent', () => {
  let component: InsurancePolicyPersonTableComponent;
  let fixture: ComponentFixture<InsurancePolicyPersonTableComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ InsurancePolicyPersonTableComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(InsurancePolicyPersonTableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

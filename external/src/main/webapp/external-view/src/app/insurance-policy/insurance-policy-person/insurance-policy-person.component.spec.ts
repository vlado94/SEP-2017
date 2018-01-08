import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { InsurancePolicyPersonComponent } from './insurance-policy-person.component';

describe('InsurancePolicyPersonComponent', () => {
  let component: InsurancePolicyPersonComponent;
  let fixture: ComponentFixture<InsurancePolicyPersonComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ InsurancePolicyPersonComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(InsurancePolicyPersonComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

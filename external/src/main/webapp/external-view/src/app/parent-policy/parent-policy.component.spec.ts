import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ParentPolicyComponent } from './parent-policy.component';

describe('ParentPolicyComponent', () => {
  let component: ParentPolicyComponent;
  let fixture: ComponentFixture<ParentPolicyComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ParentPolicyComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ParentPolicyComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

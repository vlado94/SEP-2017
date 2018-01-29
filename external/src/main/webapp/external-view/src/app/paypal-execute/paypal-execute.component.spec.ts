import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PaypalExecuteComponent } from './paypal-execute.component';

describe('PaypalExecuteComponent', () => {
  let component: PaypalExecuteComponent;
  let fixture: ComponentFixture<PaypalExecuteComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PaypalExecuteComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PaypalExecuteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CategoryFactorComponent } from './category-factor.component';

describe('CategoryFactorComponent', () => {
  let component: CategoryFactorComponent;
  let fixture: ComponentFixture<CategoryFactorComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CategoryFactorComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CategoryFactorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

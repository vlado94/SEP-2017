import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CategoryFactorFormComponent } from './category-factor-form.component';

describe('CategoryFactorFormComponent', () => {
  let component: CategoryFactorFormComponent;
  let fixture: ComponentFixture<CategoryFactorFormComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CategoryFactorFormComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CategoryFactorFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

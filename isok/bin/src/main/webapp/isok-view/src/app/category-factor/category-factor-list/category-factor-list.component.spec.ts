import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CategoryFactorListComponent } from './category-factor-list.component';

describe('CategoryFactorListComponent', () => {
  let component: CategoryFactorListComponent;
  let fixture: ComponentFixture<CategoryFactorListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CategoryFactorListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CategoryFactorListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

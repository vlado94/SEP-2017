import { TestBed, inject } from '@angular/core/testing';

import { CategoryFactorService } from './category-factor.service';

describe('CategoryFactorService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [CategoryFactorService]
    });
  });

  it('should be created', inject([CategoryFactorService], (service: CategoryFactorService) => {
    expect(service).toBeTruthy();
  }));
});

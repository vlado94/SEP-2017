import { TestBed, inject } from '@angular/core/testing';

import { InsurancePolicyService } from './insurance-policy.service';

describe('InsurancePolicyService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [InsurancePolicyService]
    });
  });

  it('should be created', inject([InsurancePolicyService], (service: InsurancePolicyService) => {
    expect(service).toBeTruthy();
  }));
});

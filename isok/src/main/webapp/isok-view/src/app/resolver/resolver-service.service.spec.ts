import { TestBed, inject } from '@angular/core/testing';

import { ResolverServiceService } from './resolver-service.service';

describe('ResolverServiceService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [ResolverServiceService]
    });
  });

  it('should be created', inject([ResolverServiceService], (service: ResolverServiceService) => {
    expect(service).toBeTruthy();
  }));
});

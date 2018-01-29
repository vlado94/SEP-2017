import { TestBed, inject } from '@angular/core/testing';

import { PaypalExecuteServiceService } from './paypal-execute-service.service';

describe('PaypalExecuteServiceService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [PaypalExecuteServiceService]
    });
  });

  it('should be created', inject([PaypalExecuteServiceService], (service: PaypalExecuteServiceService) => {
    expect(service).toBeTruthy();
  }));
});

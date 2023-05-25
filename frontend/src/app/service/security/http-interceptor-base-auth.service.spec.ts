import { TestBed } from '@angular/core/testing';

import { HttpInterceptorBaseAuthService } from './http-interceptor-base-auth.service';

describe('HttpInterceptorBaseAuthService', () => {
  let service: HttpInterceptorBaseAuthService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(HttpInterceptorBaseAuthService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});

import {
  HttpEvent,
  HttpHandler,
  HttpInterceptor,
  HttpRequest,
} from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AuthenticationServiceService } from './authentication-service.service';
@Injectable({
  providedIn: 'root',
})
export class HttpInterceptorBaseAuthService implements HttpInterceptor {
  constructor(private auth: AuthenticationServiceService) {}

  intercept(
    req: HttpRequest<any>,
    next: HttpHandler
  ): Observable<HttpEvent<any>> {
    alert;
    if (this.auth.getAuthentication() && this.auth.getToken()) {
      const token: string = this.auth.getToken()!;
      req = req.clone({
        setHeaders: {
          Authorization: token,
        },
      });
    }
    return next.handle(req);
  }
}

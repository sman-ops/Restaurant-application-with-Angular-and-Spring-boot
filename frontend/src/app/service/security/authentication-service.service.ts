import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { map } from 'rxjs/operators';
import { CookieService } from 'ngx-cookie-service';
@Injectable({
  providedIn: 'root',
})
export class AuthenticationServiceService {
  private apiServerUrl = environment.apiBaseUrl;
  constructor(private http: HttpClient, private cook: CookieService) {}

  public executeAuthentication(email: any, password: any): Observable<any> {
    return this.http
      .post<any>(`${this.apiServerUrl}/signin`, { email, password })
      .pipe(
        map((response) => {
          localStorage.setItem('email', response.email);
          localStorage.setItem('token', `Bearer ${response.token}`);
          this.cook.set('email', response.email);
          this.cook.set('token', `Bearer ${response.token}`);
        })
      );
  }

  public userActive(email: any, password: any): Observable<any> {
    return this.http
      .post<any>(`${this.apiServerUrl}/active`, { email, password })
      .pipe(
        map((response) => {
          return response;
        })
      );
  }

  public createUser(email: any, password: any): Observable<any> {
    return this.http
      .post<any>(`${this.apiServerUrl}/signup`, {
        email,
        password,
      })
      .pipe(map((response: any) => response));
  }

  // verify if a user is logged in or not

  public getAuthentication() {
    return localStorage.getItem('email') ? localStorage.getItem('email') : '';
  }

  public getToken() {
    if (this.getAuthentication()) {
      return localStorage.getItem('token');
    } else {
      return '';
    }
  }

  public isLogin() {
    return !(
      localStorage.getItem('email') == null ||
      localStorage.getItem('token') == null
    );
  }

  public logOut() {
    localStorage.removeItem('token');
    localStorage.removeItem('email');
    this.cook.delete('email');
    this.cook.delete('token');
  }
}

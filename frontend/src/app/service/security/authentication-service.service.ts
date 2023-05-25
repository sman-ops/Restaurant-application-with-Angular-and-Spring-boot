import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { map } from 'rxjs/operators';
@Injectable({
  providedIn: 'root',
})
export class AuthenticationServiceService {
  private apiServerUrl = environment.apiBaseUrl;
  constructor(private http: HttpClient) {}

  public executeAuthentication(email: any, password: any): Observable<any> {
    return this.http
      .post<any>(`${this.apiServerUrl}/signin`, { email, password })
      .pipe(
        map((response) => {
          localStorage.setItem('email', response.email);
          localStorage.setItem('token', `Bearer ${response.token}`);
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
}

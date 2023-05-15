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
      .post<any>(`${this.apiServerUrl}/login`, { email, password })
      .pipe(map((response: any) => response));
  }
}

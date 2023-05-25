import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Category } from '../model/category';
import { map } from 'rxjs/operators';
@Injectable({
  providedIn: 'root',
})
export class CategoryServiceService {
  private apiServerUrl = environment.apiBaseUrl;
  constructor(private http: HttpClient) {}

  public getAllCategories(): Observable<Category[]> {
    let head = new HttpHeaders({
      Authorization: JSON.parse(localStorage.getItem('token') || ''),
    });
    return this.http
      .get<Category[]>(`${this.apiServerUrl}/api/allCategories`, {
        headers: head,
      })
      .pipe(map((response: Category[]) => response));
  }
}

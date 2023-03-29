import { HttpClient } from '@angular/common/http';
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
    return this.http
      .get<Category[]>(`${this.apiServerUrl}/api/allCategories`)
      .pipe(map((response: Category[]) => response));
  }
}

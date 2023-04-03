import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Order } from '../../app/model/order';
@Injectable({
  providedIn: 'root',
})
export class OrderService {
  private apiServerUrl = environment.apiBaseUrl;
  constructor(private http: HttpClient) {}

  // dans get<Order[]> : la méthode get nous retourne array of order
  public getOrders(): Observable<Order[]> {
    return this.http.get<Order[]>(`${this.apiServerUrl}/api/allOrders`);
  }

  public getOrdersByCategoryId(id: any): Observable<Order[]> {
    return this.http.get<Order[]>(`${this.apiServerUrl}/api/category?id=${id}`);
  }
  public getOrdersByKey(word: any): Observable<Order[]> {
    return this.http.get<Order[]>(
      `${this.apiServerUrl}/api/orderKey?word=${word}`
    );
  }
}

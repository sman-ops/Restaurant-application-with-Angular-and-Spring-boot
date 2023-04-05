import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Order } from '../../app/model/order';
import { map } from 'rxjs/operators';
@Injectable({
  providedIn: 'root',
})
export class OrderService {
  private apiServerUrl = environment.apiBaseUrl;
  constructor(private http: HttpClient) {}

  // dans get<Order[]> : la méthode get nous retourne array of order
  public getOrders(page: any, size: any): Observable<Order[]> {
    return this.http.get<Order[]>(
      `${this.apiServerUrl}/api/allOrders?page=${page}&size=${size}`
    );
  }

  public getOrdersByCategoryId(
    id: any,
    page: any,
    size: any
  ): Observable<Order[]> {
    return this.http.get<Order[]>(
      `${this.apiServerUrl}/api/category?id=${id}&page=${page}&size=${size}`
    );
  }
  public getOrdersByKey(word: any, page: any, size: any): Observable<Order[]> {
    return this.http.get<Order[]>(
      `${this.apiServerUrl}/api/orderKey?word=${word}&page=${page}&size=${size}`
    );
  }

  public getOrderById(id: any): Observable<Order> {
    return this.http
      .get<Order>(`${this.apiServerUrl}/api/order?id=${id}`)
      .pipe(map((response: Order) => response));
  }
}

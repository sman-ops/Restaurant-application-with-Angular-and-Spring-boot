import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { HttpClient, HttpHeaders } from '@angular/common/http';
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
    let headers = new HttpHeaders();
    headers = headers.set(
      'Authorization',
      JSON.stringify(localStorage.getItem('token'))
    );

    return this.http
      .get<Order[]>(
        `${this.apiServerUrl}/api/allOrders?page=${page}&size=${size}`,
        { headers: headers }
      )
      .pipe(map((response: Order[]) => response));
  }

  public getOrdersByCategoryId(
    id: any,
    page: any,
    size: any
  ): Observable<Order[]> {
    return this.http
      .get<Order[]>(
        `${this.apiServerUrl}/api/category?id=${id}&page=${page}&size=${size}`
      )
      .pipe(map((response: Order[]) => response));
  }
  public getOrdersByKey(word: any, page: any, size: any): Observable<Order[]> {
    return this.http
      .get<Order[]>(
        `${this.apiServerUrl}/api/orderKey?word=${word}&page=${page}&size=${size}`
      )
      .pipe(map((response: Order[]) => response));
  }

  public getOrderById(id: any): Observable<Order> {
    return this.http
      .get<Order>(`${this.apiServerUrl}/api/order?id=${id}`)
      .pipe(map((response: Order) => response));
  }

  public getOrdersLength(): Observable<number> {
    let head = new HttpHeaders({
      Authorization: JSON.stringify(localStorage.getItem('token')),
    });
    return this.http
      .get<number>(`${this.apiServerUrl}/api/orderSize`, { headers: head })
      .pipe(map((response: number) => response));
  }

  public getOrdersLengthByCategoryId(id: any): Observable<number> {
    return this.http
      .get<number>(`${this.apiServerUrl}/api/categoryidSize?id=${id}`)
      .pipe(map((response: number) => response));
  }

  public getOrdersLengthKey(word: any): Observable<number> {
    return this.http
      .get<number>(`${this.apiServerUrl}/api/keySize?key=${word}`)
      .pipe(map((response: number) => response));
  }
}

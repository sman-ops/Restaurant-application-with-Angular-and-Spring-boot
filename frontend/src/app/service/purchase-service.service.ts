import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { PurchaseRequest } from '../model/purchase-request';
import { Observable } from 'rxjs';
@Injectable({
  providedIn: 'root',
})
export class PurchaseServiceService {
  private apiServerUrl = environment.apiBaseUrl;
  constructor(private http: HttpClient) {}

  public getOrder(purchaseRequest: PurchaseRequest): Observable<any> {
    return this.http.post<PurchaseRequest>(
      `${this.apiServerUrl}/api/buy/purchase`,
      purchaseRequest
    );
  }
}

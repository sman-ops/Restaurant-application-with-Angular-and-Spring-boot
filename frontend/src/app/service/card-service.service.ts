import { Injectable } from '@angular/core';
import { CartOrder } from '../model/cart-order';

@Injectable({
  providedIn: 'root',
})
export class CardServiceService {
  orders: CartOrder[] = [];
  totalOrders: number = 0;
  totalprice: number = 0;

  constructor() {}

  addOrderToCard(order: CartOrder) {
    let isExist: boolean = false;
    let existOrder!: CartOrder;
    if (this.orders.length > 0) {
      for (let temp of this.orders) {
        if (temp.id === order.id) {
          existOrder = temp;
          break;
        }
      }
    }
    isExist = existOrder != undefined; // true
    if (isExist) {
      existOrder.quantity++;
    } else {
      this.orders.push(order);
    }
    console.log(this.orders);
  }
}

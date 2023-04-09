import { Injectable } from '@angular/core';
import { CartOrder } from '../model/cart-order';
import { Subject } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class CardServiceService {
  orders: CartOrder[] = [];
  totalOrders: Subject<number> = new Subject<number>();
  totalprice: Subject<number> = new Subject<number>();

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
    this.calcultateTotal();
  }

  calcultateTotal() {
    let totalElementsSizeOrder: number = 0;
    let totalPriceOrders: number = 0;
    for (let temp of this.orders) {
      totalElementsSizeOrder += temp.quantity;
      totalPriceOrders += temp.quantity * temp.price;
    }
    this.totalprice.next(totalPriceOrders);
    this.totalOrders.next(totalElementsSizeOrder);
    console.log('size ' + this.totalprice);
    console.log('price ' + this.totalOrders);
  }
}

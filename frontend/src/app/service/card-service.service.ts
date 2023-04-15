import { Injectable } from '@angular/core';
import { CartOrder } from '../model/cart-order';
import { BehaviorSubject, Subject } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class CardServiceService {
  orders: CartOrder[] = [];
  totalOrders: Subject<number> = new BehaviorSubject<number>(0);
  totalprice: Subject<number> = new BehaviorSubject<number>(0);

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

  removeOrder(order: CartOrder) {
    order.quantity--;
    if (order.quantity === 0) {
      this.remove(order);
    } else {
      this.calcultateTotal();
    }
  }

  remove(order: CartOrder) {
    // index or -1
    const index = this.orders.findIndex((temp) => temp.id === order.id);
    if (index > -1) {
      this.orders.splice(index, 1);
      this.calcultateTotal();
    }
  }
}

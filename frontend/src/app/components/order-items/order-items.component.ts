import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { OrderService } from 'src/app/service/order.service';

import { Order } from '../../model/order';
@Component({
  selector: 'app-order-items',
  templateUrl: './order-items.component.html',
  styleUrls: ['./order-items.component.css'],
})
export class OrderItemsComponent implements OnInit {
  page: number = 1;
  pageLength: number = 2;
  orderSize: number = 10;
  orders: Order[] = [];

  constructor(
    private orderService: OrderService,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.route.paramMap.subscribe(() => {
      this.finichOrders();
    });
  }

  finichOrders() {
    let result1 = this.route.snapshot.paramMap.has('id');
    let result2 = this.route.snapshot.paramMap.has('key');
    if (result1 === true) {
      this.getOrdersByCategoryId();
    } else if (result2) {
      this.getAllOrdersContainingKey();
    } else {
      console.log('test');
      this.getAllOrders();
    }
  }

  getAllOrders(): void {
    this.orderService
      .getOrders(this.page - 1, this.pageLength)
      .subscribe((data: Order[]) => {
        console.log(data);
        this.orders = data;
      });
  }

  getOrdersByCategoryId(): void {
    let idCategory = this.route.snapshot.paramMap.get('id');
    this.orderService
      .getOrdersByCategoryId(idCategory, this.page - 1, this.pageLength)
      .subscribe((data) => {
        console.log(data);
        this.orders = data;
      });
  }

  getAllOrdersContainingKey() {
    let keyWord = this.route.snapshot.paramMap.get('key');
    this.orderService
      .getOrdersByKey(keyWord, this.page - 1, this.pageLength)
      .subscribe((data) => {
        this.orders = data;
      });
  }

  doing() {
    this.finichOrders();
  }
}

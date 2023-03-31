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
    let result = this.route.snapshot.paramMap.has('id');
    if (result === true) {
      this.getOrdersByCategoryId();
    } else {
      console.log('test');
      this.getAllOrders();
    }
  }

  getAllOrders(): void {
    this.orderService.getOrders().subscribe((data: Order[]) => {
      console.log(data);
      this.orders = data;
    });
  }

  getOrdersByCategoryId(): void {
    let idCategory = this.route.snapshot.paramMap.get('id');
    this.orderService.getOrdersByCategoryId(idCategory).subscribe((data) => {
      console.log(data);
      this.orders = data;
    });
  }
}

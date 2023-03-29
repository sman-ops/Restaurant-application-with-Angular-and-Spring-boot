import { Component, OnInit } from '@angular/core';
import { OrderService } from 'src/app/service/order.service';

import { Order } from '../../model/order';
@Component({
  selector: 'app-order-items',
  templateUrl: './order-items.component.html',
  styleUrls: ['./order-items.component.css'],
})
export class OrderItemsComponent implements OnInit {
  orders: Order[] = [];

  constructor(private orderService: OrderService) {}

  ngOnInit(): void {
    console.log('test');
    this.getAllOrders();
  }

  getAllOrders(): void {
    this.orderService.getOrders().subscribe((data: Order[]) => {
      console.log(data);
      this.orders = data;
    });
  }
}

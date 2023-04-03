import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { OrderService } from 'src/app/service/order.service';

import { Order } from '../../model/order';
@Component({
  selector: 'app-orders-details',
  templateUrl: './orders-details.component.html',
  styleUrls: ['./orders-details.component.css'],
})
export class OrdersDetailsComponent implements OnInit {
  order!: Order;
  constructor(
    private orderService: OrderService,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.getOrderById();
  }

  getOrderById() {
    let id = this.route.snapshot.paramMap.get('id');
    this.orderService.getOrderById(id).subscribe((data) => {
      this.order = data;
    });
  }
}

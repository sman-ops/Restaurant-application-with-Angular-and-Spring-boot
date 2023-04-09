import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { OrderService } from 'src/app/service/order.service';
import {} from 'src/app/service/category-service.service';
import { Order } from '../../model/order';
import { CardServiceService } from 'src/app/service/card-service.service';
import { CartOrder } from 'src/app/model/cart-order';
@Component({
  selector: 'app-orders-details',
  templateUrl: './orders-details.component.html',
  styleUrls: ['./orders-details.component.css'],
})
export class OrdersDetailsComponent implements OnInit {
  order!: Order;
  constructor(
    private orderService: OrderService,
    private route: ActivatedRoute,
    private cartService: CardServiceService
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

  addToCard(order: Order) {
    const orderCart = new CartOrder(order);
    this.cartService.addOrderToCard(orderCart);
  }
}

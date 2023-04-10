import { Component, OnInit } from '@angular/core';
import { CartOrder } from 'src/app/model/cart-order';
import { CardServiceService } from 'src/app/service/card-service.service';

@Component({
  selector: 'app-purchases',
  templateUrl: './purchases.component.html',
  styleUrls: ['./purchases.component.css'],
})
export class PurchasesComponent implements OnInit {
  orders: CartOrder[] = [];

  constructor(private cardService: CardServiceService) {}

  ngOnInit(): void {
    this.getAllOrders();
  }

  getAllOrders() {
    this.orders = this.cardService.orders;
  }
}

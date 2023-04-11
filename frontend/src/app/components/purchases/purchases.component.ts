import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CartOrder } from 'src/app/model/cart-order';
import { CardServiceService } from 'src/app/service/card-service.service';

@Component({
  selector: 'app-purchases',
  templateUrl: './purchases.component.html',
  styleUrls: ['./purchases.component.css'],
})
export class PurchasesComponent implements OnInit {
  orders: CartOrder[] = [];

  totalOrder: number = 0;
  totalPrice: number = 0;

  constructor(
    private cardService: CardServiceService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.getAllOrders();
    this.geTotals();
    this.cardService.calcultateTotal();
  }

  geTotals() {
    this.cardService.totalOrders.subscribe((data) => {
      this.totalOrder = data;
    });
    this.cardService.totalprice.subscribe((data) => {
      this.totalPrice = data;
    });
  }

  getAllOrders() {
    this.orders = this.cardService.orders;
  }

  addOrder(temp: CartOrder) {
    this.cardService.addOrderToCard(temp);
  }

  removeOrder(temp: CartOrder) {
    this.cardService.removeOrder(temp);
  }

  remove(temp: CartOrder) {
    this.cardService.remove(temp);
  }

  checkOut() {
    this.router.navigateByUrl('/checkout');
  }
}

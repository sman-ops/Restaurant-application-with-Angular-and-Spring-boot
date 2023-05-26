import { Component, OnInit } from '@angular/core';
import { CardServiceService } from 'src/app/service/card-service.service';
import {} from 'src/app/service/category-service.service';
import { AuthenticationServiceService } from 'src/app/service/security/authentication-service.service';
@Component({
  selector: 'app-card-status',
  templateUrl: './card-status.component.html',
  styleUrls: ['./card-status.component.css'],
})
export class CardStatusComponent implements OnInit {
  orderSize: number = 0;
  orderPrice: number = 0;

  constructor(
    private cartService: CardServiceService,
    private auth: AuthenticationServiceService
  ) {}

  ngOnInit(): void {
    this.getCartStatus();
  }

  getCartStatus() {
    this.cartService.totalOrders.subscribe((data) => (this.orderSize = data));
    this.cartService.totalprice.subscribe((data) => (this.orderPrice = data));
  }

  isUserLogin() {
    return this.auth.isLogin();
  }
}

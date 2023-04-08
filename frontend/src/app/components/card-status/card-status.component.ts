import { Component, OnInit } from '@angular/core';
import { CardServiceService } from 'src/app/service/card-service.service';
import {} from 'src/app/service/category-service.service';

@Component({
  selector: 'app-card-status',
  templateUrl: './card-status.component.html',
  styleUrls: ['./card-status.component.css'],
})
export class CardStatusComponent implements OnInit {
  orderSize: number = 0;
  orderPrice: number = 0;

  constructor(private cartService: CardServiceService) {}

  ngOnInit(): void {
    this.getCartStatus();
  }

  getCartStatus() {
    this.orderSize = this.cartService.totalOrders;
    this.orderPrice = this.cartService.totalprice;
  }
}

import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Order } from 'src/app/model/order';
import { OrderService } from 'src/app/service/order.service';
import { AuthenticationServiceService } from 'src/app/service/security/authentication-service.service';

@Component({
  selector: 'app-search-order',
  templateUrl: './search-order.component.html',
  styleUrls: ['./search-order.component.css'],
})
export class SearchOrderComponent implements OnInit {
  constructor(
    private orderService: OrderService,
    private router: Router,
    private auth: AuthenticationServiceService
  ) {}

  ngOnInit(): void {}

  doSearch(value: string) {
    this.router.navigateByUrl('/orders/' + value);
  }

  isAuthenticateUser() {
    return this.auth.isLogin();
  }

  logout() {
    this.auth.logOut();
    this.router.navigateByUrl('/login');
  }
}

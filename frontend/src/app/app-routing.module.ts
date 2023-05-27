import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CategoryItemsComponent } from './components/category-items/category-items.component';
import { CheckOutComponent } from './components/check-out/check-out.component';
import { OrderItemsComponent } from './components/order-items/order-items.component';
import { OrdersDetailsComponent } from './components/orders-details/orders-details.component';
import { PurchasesComponent } from './components/purchases/purchases.component';
import { LoginComponent } from './components/login/login.component';
import { SignupComponent } from './components/signup/signup.component';
import { RouteActivateService } from './service/activated/route-activate.service';
import { LoginActiveService } from './service/activated/login-active.service';

const routes: Routes = [
  // http://localhost:4200/category/2
  {
    path: 'login',
    component: LoginComponent,
    canActivate: [LoginActiveService],
  },
  {
    path: 'signup',
    component: SignupComponent,
    canActivate: [LoginActiveService],
  },
  {
    path: 'checkout',
    component: CheckOutComponent,
    canActivate: [RouteActivateService],
  },
  {
    path: 'purchases',
    component: PurchasesComponent,
    canActivate: [RouteActivateService],
  },
  {
    path: 'category/:id',
    component: OrderItemsComponent,
    canActivate: [RouteActivateService],
  },
  {
    path: 'order/:id',
    component: OrdersDetailsComponent,
    canActivate: [RouteActivateService],
  },
  {
    path: 'category',
    component: OrderItemsComponent,
    canActivate: [RouteActivateService],
  },
  {
    path: 'orders/:key  ',
    component: OrderItemsComponent,
    canActivate: [RouteActivateService],
  },
  {
    path: 'orders',
    component: OrderItemsComponent,
    canActivate: [RouteActivateService],
  },
  { path: '', redirectTo: '/orders', pathMatch: 'full' },
  // if user enter  anything without  all routes
  // { path: '**', redirectTo: '/orders', pathMatch: 'full' },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}

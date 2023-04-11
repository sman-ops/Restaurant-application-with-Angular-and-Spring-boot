import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CategoryItemsComponent } from './components/category-items/category-items.component';
import { CheckOutComponent } from './components/check-out/check-out.component';
import { OrderItemsComponent } from './components/order-items/order-items.component';
import { OrdersDetailsComponent } from './components/orders-details/orders-details.component';
import { PurchasesComponent } from './components/purchases/purchases.component';

const routes: Routes = [
  // http://localhost:4200/category/2
  { path: 'checkout', component: CheckOutComponent },
  { path: 'purchases', component: PurchasesComponent },
  { path: 'category/:id', component: OrderItemsComponent },
  { path: 'order/:id', component: OrdersDetailsComponent },
  { path: 'category', component: OrderItemsComponent },
  { path: 'orders/:key  ', component: OrderItemsComponent },
  { path: 'orders', component: OrderItemsComponent },
  { path: '', redirectTo: '/orders', pathMatch: 'full' },
  // if user enter  anything without  all routes
  // { path: '**', redirectTo: '/orders', pathMatch: 'full' },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}

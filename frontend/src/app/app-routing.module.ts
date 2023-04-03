import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CategoryItemsComponent } from './components/category-items/category-items.component';
import { OrderItemsComponent } from './components/order-items/order-items.component';

const routes: Routes = [
  // http://localhost:4200/category/2
  { path: 'category/:id', component: OrderItemsComponent },
  { path: 'category', component: OrderItemsComponent },
  { path: 'orders/:key', component: OrderItemsComponent },
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

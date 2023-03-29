import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { OrderItemsComponent } from './components/order-items/order-items.component';

const routes: Routes = [{ path: 'order', component: OrderItemsComponent }];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}

import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { OrderItemsComponent } from './components/order-items/order-items.component';
import { CategoryItemsComponent } from './components/category-items/category-items.component';
import { DropdownMenuComponent } from './components/dropdown-menu/dropdown-menu.component';
import { SearchOrderComponent } from './components/search-order/search-order.component';
import { OrdersDetailsComponent } from './components/orders-details/orders-details.component';
import { NgxPaginationModule } from 'ngx-pagination';
import { NgbPaginationModule } from '@ng-bootstrap/ng-bootstrap';
import { CardStatusComponent } from './components/card-status/card-status.component';
import { PurchasesComponent } from './components/purchases/purchases.component';
@NgModule({
  declarations: [
    AppComponent,
    OrderItemsComponent,
    CategoryItemsComponent,
    DropdownMenuComponent,
    SearchOrderComponent,
    OrdersDetailsComponent,
    CardStatusComponent,
    PurchasesComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    NgbPaginationModule,
  ],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}

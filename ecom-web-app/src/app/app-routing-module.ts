import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {ProductsComponent} from './products/products';
import {CustomersComponent} from './customers/customers';
import {OrdersComponent} from './orders/orders';
import {OrderDetailsComponent} from './order-details/order-details';

const routes: Routes = [
  {
    path: "", redirectTo: "/products", pathMatch: "full"
  },
  {
    path: "products", component: ProductsComponent
  },
  {
    path: "customers", component: CustomersComponent
  },
  {
    path: "orders/:customerId", component: OrdersComponent
  },
  {
    path: "order-details/:orderId", component: OrderDetailsComponent
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}

import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing-module';
import { App } from './app';

import { ProductsComponent } from './products/products';
import { CustomersComponent } from './customers/customers';
import { OrdersComponent } from './orders/orders';
import { OrderDetailsComponent } from './order-details/order-details';

// Tu n'importes plus HttpClientModule ici
// import { HttpClientModule } from '@angular/common/http';

import { provideHttpClient, withFetch } from '@angular/common/http';

@NgModule({
  declarations: [
    App,
    ProductsComponent,
    CustomersComponent,
    OrdersComponent,
    OrderDetailsComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    // Supprime HttpClientModule d'ici
    // HttpClientModule
  ],
  providers: [
    provideHttpClient(withFetch())
  ],
  bootstrap: [App]
})
export class AppModule { }

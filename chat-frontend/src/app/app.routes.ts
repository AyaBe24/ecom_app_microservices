import { Routes } from '@angular/router';
import { CustomersComponent } from './components/customers/customers';
import { ProductsComponent } from './components/products/products';
import { BillsComponent } from './components/bills/bills';
import { ChatComponent } from './components/chat/chat';

export const routes: Routes = [
    { path: 'customers', component: CustomersComponent },
    { path: 'products', component: ProductsComponent },
    { path: 'bills', component: BillsComponent },
    { path: 'chat', component: ChatComponent },
    { path: '', redirectTo: '/chat', pathMatch: 'full' }
];


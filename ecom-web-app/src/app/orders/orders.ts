import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-orders',
  standalone: false,
  templateUrl: './orders.html',
  styleUrl: './orders.css'
})
export class OrdersComponent implements OnInit {
  orders: any;
  customerId!: number;
  ordersList: any[] = [];
  errorMessage: string = "";

  constructor(private http: HttpClient, private router: Router, private route: ActivatedRoute, private cdr: ChangeDetectorRef) {
    this.customerId = this.route.snapshot.params['customerId'];
  }
  ngOnInit() {
    this.http.get('http://localhost:8880/billing-service/bills/full/byCustomer/' + this.customerId)
      .subscribe(
        {
          next: (data: any) => {
            console.log('Orders data received:', data);
            this.orders = data;
            this.ordersList = data;
            if (Array.isArray(data)) {
              console.log('Data is an array of length:', data.length);
            } else {
              console.log('Data is NOT an array:', data);
            }
            this.cdr.detectChanges(); // Force view update
          },
          error: error => {
            console.error('Error loading orders:', error);
            this.errorMessage = "Failed to load orders: " + error.message;
            this.cdr.detectChanges();
          }
        }
      )
  }

  getOrderDetails(o: any) {
    this.router.navigateByUrl('/order-details/' + o.id);
  }
}

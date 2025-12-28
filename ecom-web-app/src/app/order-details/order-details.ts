import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-order-details',
  standalone: false,
  templateUrl: './order-details.html',
  styleUrl: './order-details.css'
})
export class OrderDetailsComponent implements OnInit {
  orderDetails: any;
  orderId!: number;
  constructor(private http: HttpClient, private router: Router, private route: ActivatedRoute, private cdr: ChangeDetectorRef) {
    this.orderId = this.route.snapshot.params['orderId'];
  }
  ngOnInit() {
    this.http.get('http://localhost:8880/billing-service/bills/' + this.orderId)
      .subscribe(
        {
          next: data => {
            console.log('Order Details received:', data);
            this.orderDetails = data;
            this.cdr.detectChanges();
          },
          error: error => {
            console.error('Error loading order details:', error);
            this.cdr.detectChanges();
          }
        }
      )
  }
}

import {Component, OnInit} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Router} from '@angular/router';

@Component({
  selector: 'app-customers',
  standalone: false,
  templateUrl: './customers.html',
  styleUrl: './customers.css'
})
export class CustomersComponent implements OnInit {
  customers:any;
  error: any = null;
  loading: boolean = true;
  constructor(private http:HttpClient, private router:Router) {
  }
  ngOnInit() {
    this.loading = true;
    this.error = null;
    this.http.get('http://localhost:8880/customer-service/customers').subscribe(
      {
        next: data => {
          console.log('Customers data received:', data);
          this.customers = data;
          this.loading = false;
        },
        error: error => {
          console.error('Error loading customers:', error);
          this.error = error;
          this.loading = false;
        }
      }
    )
  }

  getOrders(c: any) {
    this.router.navigateByUrl("/orders/" + c.id);
  }
}

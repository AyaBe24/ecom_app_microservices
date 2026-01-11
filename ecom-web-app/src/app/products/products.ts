import { Component, OnInit } from '@angular/core';
import {HttpClient} from '@angular/common/http';

@Component({
  selector: 'app-products',
  standalone: false,
  templateUrl: './products.html',
  styleUrl: './products.css'
})
export class ProductsComponent implements OnInit{
  products:any;
  error: any = null;
  loading: boolean = true;
  constructor(private http:HttpClient) {
  }
  ngOnInit() {
    this.loading = true;
    this.error = null;
    this.http.get('http://localhost:8880/inventory-service/products').subscribe(
      {
        next: data => {
          console.log('Products data received:', data);
          this.products = data;
          this.loading = false;
        },
        error: error => {
          console.error('Error loading products:', error);
          this.error = error;
          this.loading = false;
        }
      }
    )
  }

}

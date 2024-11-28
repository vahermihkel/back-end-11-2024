import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Product } from '../models/Product';

@Injectable({
  providedIn: 'root'
})
export class ProductService {
  private url = "http://localhost:8080/products";

  constructor(private http: HttpClient) { }

  getProducts(): Observable<any> {
    return this.http.get<any>(this.url);
  }

  getProduct(name: string): Observable<Product> {
    return this.http.get<Product>("http://localhost:8080/product/" + name);
  }

  addProduct(product: Product): Observable<Product[]> {
    return this.http.post<Product[]>(this.url, product);
  }
}

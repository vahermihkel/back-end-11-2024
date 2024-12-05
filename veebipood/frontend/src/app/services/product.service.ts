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

  getProducts(pageNumber: number, pageSize: number): Observable<any> {
    return this.http.get<any>(this.url, {params: {page: pageNumber - 1, size: pageSize}});
  }

  getAllProducts(): Observable<any> {
    return this.http.get<any>("http://localhost:8080/all-products");
  }

  getProduct(name: string): Observable<Product> {
    return this.http.get<Product>("http://localhost:8080/product/" + name);
  }

  addProduct(product: Product): Observable<Product[]> {
    return this.http.post<Product[]>(this.url, product);
  }

  getCategoryProducts(categoryId: number): Observable<Product[]> {
    return this.http.get<Product[]>("http://localhost:8080/category-products", 
            {params: {categoryId}});
  }
}

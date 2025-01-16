import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Product } from '../models/Product';

type Params = {
  search: string,
  activeCategoryId: number, 
  currentPage: number, 
  pageSize: number, 
  sort: string, 
  direction: string
}

@Injectable({
  providedIn: 'root'
})
export class ProductService {
  private url = "http://localhost:8080/products";

  constructor(private http: HttpClient) { }

  getProducts(params: Params): Observable<any> {
    // http://localhost:8080/products?page=0&size=2&sort=name,desc
    return this.http.get<any>(this.url, {params: {
      search: params.search,
      categoryId: params.activeCategoryId,
      page: params.currentPage - 1, 
      size: params.pageSize, 
      sort: params.sort + "," + params.direction}});
  }

  getAllProducts(): Observable<any> {
    return this.http.get<any>("http://localhost:8080/all-products", 
      {headers: {"Authorization": "Bearer " + sessionStorage.getItem("token")}}
    );
  }

  getProduct(name: string): Observable<Product> {
    return this.http.get<Product>("http://localhost:8080/product/" + name);
  }

  addProduct(product: Product): Observable<Product[]> {
    return this.http.post<Product[]>(this.url, product,
      {headers: {"Authorization": "Bearer " + sessionStorage.getItem("token")}}
    );
  }

  getCategoryProducts(categoryId: number): Observable<Product[]> {
    return this.http.get<Product[]>("http://localhost:8080/category-products", 
            {params: {categoryId}});
  }

  deleteProduct(name: string): Observable<Product[]>  {
      return this.http.delete<any[]>(this.url + "/" + name,
        {headers: {"Authorization": "Bearer " + sessionStorage.getItem("token")}}
      );
    }

  editProduct(product: Product): Observable<Product> {
    return this.http.put<Product>(this.url, product,
      {headers: {"Authorization": "Bearer " + sessionStorage.getItem("token")}}
    );
  }
}

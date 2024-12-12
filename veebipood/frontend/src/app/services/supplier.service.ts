import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class SupplierService {
  private url = "http://localhost:8080"

  constructor(private http: HttpClient) { }

  getStoreProducts() {
    return this.http.get(this.url + "/store-products");
  }

  getEscuelaProducts() {
    return this.http.get(this.url + "/escuela-products");
  }

  getBooks(search: string) {
    return this.http.get(this.url + "/books", {params: {search}});
  }
}

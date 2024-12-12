import { Injectable } from '@angular/core';
import { Product } from '../models/Product';
import { HttpClient } from '@angular/common/http';
import { CartProduct } from '../models/CartProduct';

@Injectable({
  providedIn: 'root'
})
export class CartService {
  private cart: CartProduct[] = JSON.parse(localStorage.getItem("cart") || "[]");

  constructor(private http: HttpClient) { }

  saveOrder(personId: number) {
    return this.http.post("http://localhost:8080/orders", 
      {"cartRows": this.cart, "person": {"id": personId}});
  }

  private saveToLocalStorage() {
    localStorage.setItem("cart", JSON.stringify(this.cart));
    console.log("TEST");
  }

  addToCart(productClicked: Product) {
    const cartRow = this.cart.find(p => p.productName === productClicked.name);
    if (cartRow !== undefined) {
      cartRow.quantity++;
    } else {
      this.cart.push({"productName": productClicked.name, "quantity": 1})
    }
    this.saveToLocalStorage();
    // JSON.parse --> võtab jutumärgid ära.
    // localStorage-sse pannes ja võttes on alati jutumärgid ümber
  }

  getCart() {
    return this.cart;
  }

  decreaseQuantity(cartRow: any) {
    cartRow.quantity--;
    if (cartRow.quantity === 0) {
      const index = this.cart.indexOf(cartRow);
      this.cart.splice(index, 1);
    }
    this.saveToLocalStorage();
  }

  increaseQuantity(cartRow: any) {
    cartRow.quantity++;
    this.saveToLocalStorage();
  }

  remove(index: number) {
    this.cart.splice(index,1);
    this.saveToLocalStorage();
  }

  getParcelMachines(country: string) {
    //return this.http.get("http://localhost:8080/parcel-machines?country=" + country)
    return this.http.get("http://localhost:8080/parcel-machines", {params: {country}})
  }
}

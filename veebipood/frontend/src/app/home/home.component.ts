import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { ProductService } from '../services/product.service';
import { CartService } from '../services/cart.service';
import { Product } from '../models/Product';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent {
  products: Product[] = [];

  // failide sidumiseks
  constructor(private productService: ProductService,
    private cartService: CartService
  ) {}

  // lehele tuleku käima minemise funktsioon
  ngOnInit(): void {
    this.productService.getProducts().subscribe(response => 
      this.products = response.content
    );
  }

  addToCart(product: Product) {
    this.cartService.addToCart(product);
  }

  // fetchProducts() {
  //   //this.products = ["Coca", "Fanta", "Sprite"];
  //   this.http.get<any[]>("http://localhost:8080/products").subscribe(response => 
  //     this.products = response
  //   );
  // }
}

import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent {
  products: any[] = [];

  // failide sidumiseks
  constructor(private http: HttpClient) {}

  // lehele tuleku käima minemise funktsioon
  ngOnInit(): void {
    this.http.get<any[]>("http://localhost:8080/products").subscribe(response => 
      this.products = response
    );
  }

  // fetchProducts() {
  //   //this.products = ["Coca", "Fanta", "Sprite"];
  //   this.http.get<any[]>("http://localhost:8080/products").subscribe(response => 
  //     this.products = response
  //   );
  // }
}

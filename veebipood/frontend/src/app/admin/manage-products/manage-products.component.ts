import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';

@Component({
  selector: 'app-manage-products',
  standalone: true,
  imports: [],
  templateUrl: './manage-products.component.html',
  styleUrl: './manage-products.component.css'
})
export class ManageProductsComponent {
  products: any[] = [];

  constructor(private http: HttpClient) {}

  ngOnInit(): void {
    this.http.get<any[]>("http://localhost:8080/products").subscribe(response => 
      this.products = response
    );
  }

  removeProduct(id: number) {
    // TODO: kustutame toote back-endist
  }
}

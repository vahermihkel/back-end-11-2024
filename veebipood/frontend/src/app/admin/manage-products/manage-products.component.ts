import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { ProductService } from '../../services/product.service';

@Component({
  selector: 'app-manage-products',
  standalone: true,
  imports: [],
  templateUrl: './manage-products.component.html',
  styleUrl: './manage-products.component.css'
})
export class ManageProductsComponent {
  products: any[] = [];

  constructor(private productService: ProductService) {}

  ngOnInit(): void {
    this.productService.getAllProducts().subscribe(response => 
      this.products = response
    );
  }

  removeProduct(id: number) {
    // TODO: kustutame toote back-endist
  }
}

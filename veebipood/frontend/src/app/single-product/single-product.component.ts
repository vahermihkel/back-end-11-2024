import { Component } from '@angular/core';
import { Product } from '../models/Product';
import { ActivatedRoute } from '@angular/router';
import { ProductService } from '../services/product.service';

@Component({
  selector: 'app-single-product',
  standalone: true,
  imports: [],
  templateUrl: './single-product.component.html',
  styleUrl: './single-product.component.css'
})
export class SingleProductComponent {
  product!: Product;
  loading = true;

  constructor(private route: ActivatedRoute,
    private productService: ProductService
  ) {}

  ngOnInit(): void {  // .get("productName")   <--->  /product/:productName
    const productName = this.route.snapshot.paramMap.get("productName");
    if (productName === null) {
      this.loading = false;
      return;
    }
    this.productService.getProduct(productName).subscribe(
      {
        next: (res) => {
          this.product = res; 
          this.loading = false
        },
        error: () => {
          this.loading = false
        }
      });
  }
}

import { Component } from '@angular/core';
import { FormsModule, NgForm } from '@angular/forms';
import { Nutrients, Product } from '../../models/Product';
import { Category } from '../../models/Category';
import { Characteristic } from '../../models/Characteristic';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-add-product',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './add-product.component.html',
  styleUrl: './add-product.component.css'
})
export class AddProductComponent {

  constructor(private http: HttpClient) {}

  onSubmit(form: NgForm) {
    const val = form.value;
    const product = new Product(0, val.name, val.price, val.active, val.image, 
      new Nutrients(0, val.protein, val.carbohydrate, val.fat),
      new Category(val.category, ""),
      [new Characteristic(val.characteristic, "")]
    );
    this.http.post("http://localhost:8080/products", product).subscribe(() => {
      form.reset();
    });
  }
}

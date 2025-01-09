import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ProductService } from '../../services/product.service';
import { FormControl, FormGroup, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { Nutrients, Product } from '../../models/Product';
import { Category } from '../../models/Category';
import { Characteristic } from '../../models/Characteristic';
import { CategoryService } from '../../services/category.service';
import { CharacteristicService } from '../../services/characteristic.service';

@Component({
  selector: 'app-edit-product',
  standalone: true,   // ReactiveForms --> need vormid mida luuakse .ts poole peal (FormGroup)
  imports: [FormsModule, ReactiveFormsModule],
  templateUrl: './edit-product.component.html',
  styleUrl: './edit-product.component.css'
})
export class EditProductComponent {
  editProductForm!: FormGroup;
  categories: Category[] = [];
  characteristics: Characteristic[] = [];
  private productName!: string;
  productCharacteristics: Characteristic[] = [];
  message = "";

  constructor(private route: ActivatedRoute,
    private productService: ProductService,
    private categoryService: CategoryService,
    private characteristicService: CharacteristicService,
    private router: Router
  ) {}

  ngOnInit(): void {
    const productName = this.route.snapshot.paramMap.get("productName");
    if (productName) {
      this.categoryService.getCategories().subscribe(res => this.categories = res);
      this.characteristicService.getCharacteristics().subscribe(res => this.characteristics = res);
      this.productService.getProduct(productName).subscribe(res => {
        this.productName = res.name;
        this.productCharacteristics = res.characteristics;
        this.editProductForm = new FormGroup({
          protein: new FormControl(res.nutrients.protein),
          carb: new FormControl(res.nutrients.carbohydrate),
          fat: new FormControl(res.nutrients.fat),
          name: new FormControl({value: res.name, disabled: true}),
          price: new FormControl(res.price),
          active: new FormControl(res.active),
          category: new FormControl(res.category.id),
          image: new FormControl(res.image)
        });
      })
    }
  }

  isChecked(characteristicId: number) {
    return this.productCharacteristics.map(c => c.id).includes(characteristicId);
  }

  toggleCharacteristics(charId: number) {
    const index = this.productCharacteristics.findIndex(char => char.id === charId)
    if (index >= 0) {
  //if (index !== -1) {
      this.productCharacteristics.splice(index,1);
    } else {
      this.productCharacteristics.push(new Characteristic(charId));
    }
  }

  onSubmit() {
    const val = this.editProductForm.value;
    const product = new Product(
          this.productName, val.price, val.active, val.image, 
          new Nutrients(val.protein, val.carb, val.fat),
          new Category(val.category),
          this.productCharacteristics
        );
        // TODO: Kui ei õnnestu, siis ei suuna
    this.productService.editProduct(product).subscribe({
      next: (res) => {this.router.navigateByUrl("/manage-products")},
      error: (res) => {
        this.message = res.error.name}
    });
  }
}

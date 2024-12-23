import { Component } from '@angular/core';
import { ProductService } from '../services/product.service';
import { CartService } from '../services/cart.service';
import { Product } from '../models/Product';
import { CategoryService } from '../services/category.service';
import { Category } from '../models/Category';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent {
  // TODO: nool vasakule leheküljenumbrite ees ja nool paremale leheküljenumbrite järel
  // kui vajutada vasakule noolele, siis vähendab leheküljenumbrit
  // kui vajutada paremale noolele, siis suurendab leheküljenumbrit
  // disabled kui on esimesel või viimasel lehel
  products: Product[] = [];
  currentPage = 1;
  pageSize = 3;
  pages: number[] = [];
  totalElements = 0;
  categories: Category[] = [];

  // failide sidumiseks
  constructor(private productService: ProductService,
    private cartService: CartService,
    private categoryService: CategoryService
  ) {}

  // lehele tuleku käima minemise funktsioon
  ngOnInit(): void {
    this.loadCategories();
    this.loadProducts();
    console.log("KÄivitasin home.componendi")
  }

  private loadCategories() {
    this.categoryService.getCategories().subscribe(res => {
      this.categories = res;
    })
  }

  private loadProducts() {
    this.productService.getProducts(this.currentPage, this.pageSize).subscribe(response => {
      this.products = response.content;
      this.totalElements = response.totalElements;
      this.pages = [];
      for (let index = 1; index <= response.totalPages; index++) {
        this.pages.push(index);
      }
    });
  }

  // TODO: getCategoryProducts tagastab mulle kõik tooted, mitte LK kaupa
  filterByCategory(categoryId: number) {
    this.productService.getCategoryProducts(categoryId).subscribe(res => {
      this.products = res;
    })

  }

  addToCart(product: Product) {
    this.cartService.addToCart(product);
  }

  changePage(newPage: number){
    this.currentPage = newPage;
    this.productService.getProducts(this.currentPage, this.pageSize).subscribe(response => 
      this.products = response.content
    );
  }

  changePageSize(newSize: number){
    this.pageSize = newSize;
    this.productService.getProducts(this.currentPage, this.pageSize).subscribe(response => {
      this.products = response.content;
      this.pages = [];
      for (let index = 1; index <= response.totalPages; index++) {
        this.pages.push(index);
      }
    });
  }
}

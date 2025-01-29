import { Component } from '@angular/core';
import { ProductService } from '../services/product.service';
import { CartService } from '../services/cart.service';
import { Product } from '../models/Product';
import { CategoryService } from '../services/category.service';
import { Category } from '../models/Category';
import { TranslatePipe } from '@ngx-translate/core';
import { RouterLink } from '@angular/router';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [TranslatePipe, RouterLink, FormsModule],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent {
  // TODO: nool vasakule leheküljenumbrite ees ja nool paremale leheküljenumbrite järel
  // kui vajutada vasakule noolele, siis vähendab leheküljenumbrit
  // kui vajutada paremale noolele, siis suurendab leheküljenumbrit
  // disabled kui on esimesel või viimasel lehel
  products: Product[] = [];
  // currentPage = 1;
  // pageSize = 3;
  pages: number[] = [];
  totalElements = 0;
  categories: Category[] = [];
  // sort = "name";
  // direction = "asc";
  // activeCategoryId = 0;
  params = {search: "", activeCategoryId: 0, currentPage: 1, pageSize: 9, sort: "name", direction: "asc"};
  searched = "";

  // failide sidumiseks
  constructor(private productService: ProductService,
    private cartService: CartService,
    private categoryService: CategoryService
  ) {}

  // lehele tuleku käima minemise funktsioon
  ngOnInit(): void {
    this.loadCategories();
    this.loadProducts(true);
    console.log("KÄivitasin home.componendi")
  }

  private loadCategories() {
    this.categoryService.getCategories().subscribe(res => {
      this.categories = res;
    })
  }

  private loadProducts(loadPages: boolean) {
    this.productService.getProducts(this.params).subscribe(response => {
      this.products = response.content;
      if (loadPages) {
        this.totalElements = response.totalElements;
        this.pages = [];
        for (let index = 1; index <= response.totalPages; index++) {
          this.pages.push(index);
        }
      }
    });
  }

  filterBySearch() {
    this.params.search = this.searched;
    this.params.currentPage = 1;
    this.loadProducts(true);
  }

  filterByCategory(categoryId: number) {
    this.params.activeCategoryId = categoryId;
    this.params.currentPage = 1;
    this.loadProducts(true);
  }

  changeSort(sort: string, direction: string) {
    this.params.sort = sort;
    this.params.direction = direction;
    this.loadProducts(false);
  }

  addToCart(product: Product) {
    this.cartService.addToCart(product);
  }

  changePage(newPage: number){
    this.params.currentPage = newPage;
    this.loadProducts(false);
  }

  changePageSize(newSize: number){
    this.params.pageSize = newSize;
    this.params.currentPage = 1;
    this.loadProducts(true);
  }
}

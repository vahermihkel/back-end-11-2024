import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CategoryService } from '../../services/category.service';

@Component({
  selector: 'app-manage-category',
  standalone: true,
  imports: [FormsModule], // siia mida ei eksisteeri tavalises HTMLs
  templateUrl: './manage-category.component.html',
  styleUrl: './manage-category.component.css'
})
export class ManageCategoryComponent {
  categories: any[] = [];
  newCategory = "";
  message = "";

  constructor(private categoryService: CategoryService) {} // siia mida ei eksisteeri tavalises JavaScriptis

  ngOnInit(): void {
    this.categoryService.getCategories().subscribe(res => 
      this.categories = res
    );
  }

  addCategory() {
    this.categoryService.addCategory(this.newCategory).subscribe(res => {
      this.categories = res;
      this.newCategory = "";
    });
  }

  removeCategory(id: number) {
    this.categoryService.deleteCategory(id).subscribe({
    next: (res) => {
      this.categories = res;
    },
    error: (message) => {
      this.message = message.error.name;
    }
    });
  }
}

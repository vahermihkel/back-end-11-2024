import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';

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

  constructor(private http: HttpClient) {} // siia mida ei eksisteeri tavalises JavaScriptis

  ngOnInit(): void {
    this.http.get<any[]>("http://localhost:8080/categories").subscribe(res => 
      this.categories = res
    );
  }

  addCategory() {
    this.http.post<any[]>("http://localhost:8080/categories", {"name": this.newCategory}).subscribe(res => 
      this.categories = res
    );
  }

  removeCategory(id: number) {
    this.http.delete<any[]>("http://localhost:8080/categories/" + id).subscribe(res =>
      this.categories = res
    );
  }
}

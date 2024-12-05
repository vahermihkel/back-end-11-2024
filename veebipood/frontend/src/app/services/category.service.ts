import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Category } from '../models/Category';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CategoryService {
  // private Service-i muutujate ees kui teda pole Componentides vaja
  private url = "http://localhost:8080/categories";

  constructor(private http: HttpClient) { }

  getCategories(): Observable<Category[]> {
    return this.http.get<Category[]>(this.url);
  }

  addCategory(newCategory: string): Observable<Category[]>  {
    return this.http.post<any[]>(this.url, {"name": newCategory})
  }

  deleteCategory(id: number): Observable<Category[]>  {
    return this.http.delete<any[]>(this.url + "/" + id);
  }

  
}

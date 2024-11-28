import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
 
@Component({
  selector: 'app-manage-characteristics',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './manage-characteristics.component.html',
  styleUrl: './manage-characteristics.component.css'
})
export class ManageCharacteristicsComponent {
  characteristics: any[] = [];
  newCharacteristic = "";
 
  // Dependency Injection -> Springis @Autowired
  constructor(private http: HttpClient) {}
 
  ngOnInit(): void {
    this.http.get<any[]>("http://localhost:8080/characteristics").subscribe(res =>
      this.characteristics = res
    );
  }
 
  addCharacteristics() {
    this.http.post<any[]>("http://localhost:8080/characteristics", {"name": this.newCharacteristic}).subscribe(res =>
      this.characteristics = res
    );
  }
 
 
  removeCharacteristics(id: number) {
    this.http.delete<any[]>("http://localhost:8080/characteristics/" + id).subscribe(res =>
      this.characteristics = res
    );
  }
}
 

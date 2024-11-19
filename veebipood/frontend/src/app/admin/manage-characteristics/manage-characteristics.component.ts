import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';

@Component({
  selector: 'app-manage-characteristics',
  standalone: true,
  imports: [],
  templateUrl: './manage-characteristics.component.html',
  styleUrl: './manage-characteristics.component.css'
})
export class ManageCharacteristicsComponent {
  characteristics: any[] = [];

  // lisamine + kustutamine

  // Dependency Injection -> Springis @Autowired
  constructor(private http: HttpClient) {}

  ngOnInit(): void {
     this.http.get<any[]>("http://localhost:8080/characteristics").subscribe(res =>
        this.characteristics = res
     )
  }
}

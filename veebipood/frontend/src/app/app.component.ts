import { Component } from '@angular/core';
import { RouterLink, RouterOutlet } from '@angular/router';
import { NavbarComponent } from './navbar/navbar.component';
import { AuthService } from './services/auth.service';
import { TranslateModule, TranslateService } from '@ngx-translate/core';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, NavbarComponent, TranslateModule], // importima KÕIK asjad mida pole tavalises HTMLs olemas
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'Veebipood';
  loading = false; // TODO: Paneme loadimise tööle

  constructor(private authService: AuthService,
    private translateService: TranslateService
  ) {
    this.translateService.addLangs(['et', 'en']);
    this.translateService.setDefaultLang('et');
  }

  ngOnInit(): void {
    this.authService.determineIfLoggedIn().subscribe(() => {
      console.log("Olen lõpetanud loadimise");
      this.loading = false
    });
  }
}

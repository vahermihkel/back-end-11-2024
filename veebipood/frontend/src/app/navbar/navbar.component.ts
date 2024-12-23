import { Component } from '@angular/core';
import { Router, RouterLink } from '@angular/router';
import { AuthService } from '../services/auth.service';

@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [RouterLink],
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.css'
})
export class NavbarComponent {
  loggedIn!: boolean;
  admin!: boolean;

  // käivitub kui componendi peale minnakse. failide sidumiseks (Spring: @Autowired)
  constructor(private authService: AuthService,
    private router: Router
  ) {}

  // käivitub siis kui componendi peale minnakse. käima minemise funktsioon. 
  // muutujate initsialiseerimiseks.
  ngOnInit(): void {
    this.authService.determineIfLoggedIn().subscribe();
    // this.loggedIn = this.authService.loggedInStatus.loggedIn;
    this.authService.loggedInStatus.subscribe(response => {
      this.loggedIn = response.loggedIn;
      this.admin = response.admin;
    });
    // .subscribe() käivitub iga kord kui this.authService.loggedInStatus.next rida käivitatakse
    console.log("Käivitasin navbari ngOnInit funktsiooni. LoggedInStatus: " + this.loggedIn);
  }

  logout() {
    sessionStorage.removeItem("token");
    sessionStorage.removeItem("expiration");
    // this.admin = false; // <--- ei muuda Subjecti sees olevaid väärtusi.
    // this.loggedIn = false;
    this.authService.loggedInStatus.next({loggedIn: false, admin: false});
    this.router.navigateByUrl("/");
  }
}

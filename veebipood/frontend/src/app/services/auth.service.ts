import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

type Token = {token: string, expiration: Date}

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private backendUrl = "http://localhost:8080";

  constructor(private http: HttpClient) { }

  login(value: {email: string, password: string}): Observable<Token> {
    return this.http.post<Token>(this.backendUrl + "/login", value);
  }
}

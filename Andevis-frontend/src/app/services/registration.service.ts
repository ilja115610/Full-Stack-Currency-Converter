import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {LoginResponse} from "../common/login-response";

@Injectable({
  providedIn: 'root'
})
export class RegistrationService {

  private URL = "http://localhost:8080/register";

  constructor(private http: HttpClient) {
  }

  getRegistered(user) {

    return this.http.post<LoginResponse>(this.URL, user);
  }
}

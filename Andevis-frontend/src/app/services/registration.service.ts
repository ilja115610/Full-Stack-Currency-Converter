import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {LoginResponse} from "../common/login-response";

@Injectable({
  providedIn: 'root'
})
export class RegistrationService {

  private URL = "http://backend-ilja115610-dev.apps.sandbox.x8i5.p1.openshiftapps.com/register";

  constructor(private http: HttpClient) {
  }

  getRegistered(user) {

    return this.http.post<LoginResponse>(this.URL, user);
  }
}

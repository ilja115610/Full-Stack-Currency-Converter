import { Injectable } from '@angular/core';
import {BehaviorSubject, Subject} from "rxjs";
import {HttpClient} from "@angular/common/http";
import {LoginResponse} from "../common/login-response";

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private authUrl = 'http://backend-ilja115610-dev.apps.sandbox.x8i5.p1.openshiftapps.com/auth';

  private logoutUrl = 'http://backend-ilja115610-dev.apps.sandbox.x8i5.p1.openshiftapps.com/logout';

  authentication: Subject<string> = new BehaviorSubject<string>(this.getToken());

  constructor(private httpClient: HttpClient) { }


  public getAuthenticationToken (request) {

    return this.httpClient.post<LoginResponse>(this.authUrl, request, { responseType: 'json'});

  }

  logout() {
    localStorage.removeItem('token')
    localStorage.removeItem('username')
    localStorage.removeItem('userId')
    this.setToken('','','');
    this.httpClient.get(this.logoutUrl);
  }

  getToken(): string {
    return localStorage.getItem('token')||'';
  }

  getUserName(): string {
    return localStorage.getItem('username')||'';
  }


  getUserId(): number {
    return parseInt(localStorage.getItem('userId')||'');
  }

  setToken(value: string, username:string, userId:string) {
    localStorage.setItem('token',value);
    localStorage.setItem('username', username);
    localStorage.setItem('userId', userId);
    this.authentication.next(this.getToken());
  }
}

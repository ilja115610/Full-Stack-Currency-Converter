import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {AuthService} from "./auth.service";
import {Observable} from "rxjs";
import {Currency} from "../common/currency";
import {Router} from "@angular/router";

@Injectable({
  providedIn: 'root'
})
export class MainService {

  private baseUrl = 'http://localhost:8080/api/exchange';


  constructor(private httpClient: HttpClient, private authService: AuthService,private router: Router) { }


  public fetchLatest(): Observable<Currency[]> {

    if(!this.authService.getToken()){
      this.router.navigate(['login']);
    }

    const headers = new HttpHeaders().set("Authorization", "Bearer "+this.authService.getToken())
    return this.httpClient.get<Currency[]>(this.baseUrl, {headers, responseType:'json'});
  }
}

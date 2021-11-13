import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {AuthService} from "./auth.service";
import {Observable} from "rxjs";
import {Currency} from "../common/currency";
import {RateResponse} from "../common/rate-response";
import {Router} from "@angular/router";


@Injectable({
  providedIn: 'root'
})
export class ExchangeService {

  private baseUrl = 'http://backend-ilja115610-dev.apps.sandbox.x8i5.p1.openshiftapps.com/api/exchange';


  constructor(private httpClient: HttpClient, private authService: AuthService, private router: Router) { }


  public getRate(request): Observable<RateResponse> {

    const URL = this.baseUrl + '/rates';

    if(!this.authService.getToken()){
      this.router.navigate(['login']);
    }

    request.userId = this.authService.getUserId();

    const headers = new HttpHeaders().set("Authorization", "Bearer "+this.authService.getToken())
    return this.httpClient.post<RateResponse>(URL, request,{headers, responseType:'json'});
  }



}

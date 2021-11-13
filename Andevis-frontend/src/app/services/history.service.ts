import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {AuthService} from "./auth.service";
import {Observable} from "rxjs";
import {RateResponse} from "../common/rate-response";
import {Record} from "../common/record";
import {Router} from "@angular/router";

@Injectable({
  providedIn: 'root'
})
export class HistoryService {

  private baseUrl = 'http://backend-ilja115610-dev.apps.sandbox.x8i5.p1.openshiftapps.com/api/exchange';


  constructor(private httpClient: HttpClient, private authService: AuthService, private router: Router) { }


  public findUserHistory(id): Observable<Record[]> {

    const URL = this.baseUrl + `/records/${id}`;

    if(!this.authService.getToken()){
      this.router.navigate(['login']);
    }
    const headers = new HttpHeaders().set("Authorization", "Bearer "+this.authService.getToken())
    return this.httpClient.get<Record[]>(URL, {headers, responseType:'json'});
  }
}

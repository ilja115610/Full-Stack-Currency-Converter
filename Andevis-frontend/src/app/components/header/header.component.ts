import { Component, OnInit } from '@angular/core';
import {AuthService} from "../../services/auth.service";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  authenticated: boolean = false;

  token: string;

  private _userName:string;

  private _userId: number;

  constructor(private authService: AuthService) {}

  ngOnInit(): void {
    this.authService.authentication.subscribe((data) => {
      this.token = data;
      this._userName = this.authService.getUserName();
      this._userId = this.authService.getUserId();
      this.authenticated = !!this.token;
    });
  }


  logout() {
    this.authService.logout();
  }


  get userName(): string {
    return this._userName;
  }


  get userId(): number {
    return this._userId;
  }

}

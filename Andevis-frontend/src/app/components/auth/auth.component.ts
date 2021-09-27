import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";
import {AuthService} from "../../services/auth.service";
import {AuthRequest} from "../../common/auth-request";

@Component({
  selector: 'app-auth',
  templateUrl: './auth.component.html',
  styleUrls: ['./auth.component.css']
})
export class AuthComponent implements OnInit {

  login: string;

  password: string;

  message: string;

  timeIsUp: boolean = true;

  authRequest = new AuthRequest();

  authToken: string;

  userName: string;

  userId: string;

  constructor(private loginService: AuthService, private router: Router) { }

  ngOnInit(): void {
  }


  getLogin (form) {

    if(this.validateForm(form)==='invalid'){
      return;
    }

    this.authRequest.login = this.login;
    this.authRequest.password = this.password;

    let response = this.loginService.getAuthenticationToken(this.authRequest);

    response.subscribe(data=>{
      this.message = data.message;
      this.authToken = data.authToken;
      this.userName = data.username;
      this.userId = String(data.userId);

      if(data.message === "Wrong username or password"){
        this.message = data.message;
        return;
      }

      this.loginService.setToken(this.authToken, this.userName, this.userId);

        this.router.navigate(["/main/exchange"])

    })

  }

  setTimer () {
    setTimeout(()=>{
      this.timeIsUp = false;
    },3000)
  }


  validateForm (form) {

    form.controls.login.touched = true;
    form.controls.password.touched = true;

    if(!form.valid){
      return 'invalid';
    }
    return 'valid';
  }

}

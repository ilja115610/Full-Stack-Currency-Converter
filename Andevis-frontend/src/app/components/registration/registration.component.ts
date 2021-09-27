import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";
import {RegistrationService} from "../../services/registration.service";
import {User} from "../../common/user";

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {

  login: string;

  password: string;

  email: string;

  constructor(private registrationService: RegistrationService, private router: Router ) { }

  ngOnInit(): void {
  }


  register(form) {

    if(this.validateForm(form)==='invalid'){
      return;
    }

    let theUser = new User();
    theUser.login = this.login;
    theUser.password = this.password;
    theUser.email = this.email;
    let response = this.registrationService.getRegistered(theUser);

    response.subscribe(data=>{
      if(data.message==='Welcome'){
        this.router.navigate(["/login"])
      }
      else {
        this.router.navigate(["/register"])
      }
    })
  }


  validateForm (form) {

    form.controls.login.touched = true;
    form.controls.password.touched = true;
    form.controls.email.touched = true;

    if(!form.valid){
      return 'invalid';
    }
    return 'valid';
  }

}

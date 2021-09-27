import { Component, OnInit } from '@angular/core';
import {NavigationEnd, NavigationStart, Router} from "@angular/router";
import {ExchangeService} from "../../services/exchange.service";
import {Location} from "@angular/common";

@Component({
  selector: 'app-conversion',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css']
})
export class MainComponent implements OnInit {

  conversionMode: boolean = true;


  constructor(private exchangeService:ExchangeService, private location: Location, private router: Router) { }

  ngOnInit(): void {

    this.setMode();

  }

  setMode() {

    this.router.events.subscribe(value => {
      if (value instanceof NavigationStart || value instanceof NavigationEnd) {
        this.conversionMode = value['url'] === '/main/exchange';
        if (value['urlAfterRedirects'] === '/main/exchange') {
          this.conversionMode = true;
        }
      }
    });
  }


}

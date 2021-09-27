import { Component, OnInit } from '@angular/core';
import {Currency} from "../../common/currency";
import {RateResponse} from "../../common/rate-response";
import {MainService} from "../../services/main.service";
import {ExchangeService} from "../../services/exchange.service";
import {RateRequest} from "../../common/rate-request";

@Component({
  selector: 'app-exchange',
  templateUrl: './exchange.component.html',
  styleUrls: ['./exchange.component.css']
})
export class ExchangeComponent implements OnInit {

  rateResponse = new RateResponse();

  rateRequest = new RateRequest();

  currencies: Currency[] = [];

  amount: number;

  sourceCurrency: string;

  targetCurrency: string;

  displayed: boolean;


  constructor(private conversionService: MainService, private exchangeService: ExchangeService) {
  }

  ngOnInit(): void {

    this.conversionService.fetchLatest().subscribe((data) => {
      this.currencies = data;
      this.sourceCurrency = this.currencies[this.currencies.length-1].currencyCode;
      this.targetCurrency = this.currencies[8].currencyCode;

    });

  }

  validateForm(form) {

    form.controls.amount.touched = true;

    if (!form.valid) {
      return 'invalid';
    }
    return 'valid';
  }

  sendRequest(form) {

    this.displayed = true;

    if (this.validateForm(form) == 'invalid') {
      return;
    }
    this.rateRequest.amount = this.amount.toFixed(3);
    this.rateRequest.sourceCurrency = this.sourceCurrency;
    this.rateRequest.targetCurrency = this.targetCurrency;
    this.exchangeService.getRate(this.rateRequest).subscribe((data) => {
      this.rateResponse = data;
    });

  }

  resetForm() {
    this.rateRequest.amount = "";
    this.rateResponse.calculatedRate = "";
  }
}

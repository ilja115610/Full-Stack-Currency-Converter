package com.exchange.andevisbackend.controller;

import com.exchange.andevisbackend.DTO.RateRequest;
import com.exchange.andevisbackend.DTO.RateResponse;
import com.exchange.andevisbackend.common.CurrencyCode;
import com.exchange.andevisbackend.entity.Currency;
import com.exchange.andevisbackend.entity.User;
import com.exchange.andevisbackend.service.ExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/exchange")
public class ExchangeController {

    private final ExchangeService exchangeService;

    @Autowired
    public ExchangeController(ExchangeService exchangeService) {
        this.exchangeService = exchangeService;
    }

    @GetMapping("")
    public Set<Currency> getCurrencyData (){

        return exchangeService.fetchCurrencyData();
    }

    @PostMapping("/rates")
    public RateResponse getRates (@RequestBody RateRequest request){

        CurrencyCode sourceCode = request.getSourceCurrency();

        CurrencyCode targetCode = request.getTargetCurrency();

        String amount = request.getAmount();

        Long userId = request.getUserId();

        return exchangeService.fetchRates(sourceCode, targetCode, userId, amount);

    }
}

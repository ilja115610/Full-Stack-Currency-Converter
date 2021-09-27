package com.exchange.andevisbackend.scheduled;


import com.exchange.andevisbackend.service.ExchangeService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class ScheduledTask {

    private final ExchangeService exchangeService;

    private static final Logger LOG = LogManager.getLogger(ScheduledTask.class);


    @Autowired
    public ScheduledTask(ExchangeService exchangeService) {
        this.exchangeService = exchangeService;
    }

    @Scheduled(cron = "1 15 16 * * *", zone="CET")
    public void refreshCurrencyData () {

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate now = LocalDate.parse(LocalDate.now().format(dtf),dtf);
        exchangeService.refreshRates(now);
        LOG.info("Rates refreshed at " + LocalDateTime.now());
    }
}

package com.exchange.andevisbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableScheduling
public class AndevisBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(AndevisBackendApplication.class, args);
    }

}

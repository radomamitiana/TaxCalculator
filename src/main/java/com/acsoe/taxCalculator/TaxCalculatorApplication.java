package com.acsoe.taxCalculator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TaxCalculatorApplication {

    public static void main(String[] args) {
        new TaxCalculatorApplication().run(args);
    }

    private void run(String[] args) {
        SpringApplication.run(TaxCalculatorApplication.class, args);
    }
}

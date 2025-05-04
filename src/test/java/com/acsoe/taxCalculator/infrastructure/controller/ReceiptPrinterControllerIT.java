package com.acsoe.taxCalculator.infrastructure.controller;

import com.acsoe.taxCalculator.domain.model.Invoice;
import com.acsoe.taxCalculator.domain.model.InvoiceDetails;
import com.acsoe.taxCalculator.domain.model.Product;
import com.acsoe.taxCalculator.domain.model.Receipt;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.stream.Stream;

import static com.acsoe.taxCalculator.domain.model.ProductCategory.BOOK;
import static com.acsoe.taxCalculator.domain.model.ProductCategory.FOOD;
import static com.acsoe.taxCalculator.domain.model.ProductCategory.OTHER;
import static com.acsoe.taxCalculator.domain.sample.InvoiceSample.oneInvoice;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.HttpStatus.OK;

@SpringBootTest(webEnvironment = RANDOM_PORT)
class ReceiptPrinterControllerIT {

    private static final String GENERATE_RECEIPT_URL = "/receipts";
    private static final BigDecimal SUM_OF_TAX = new BigDecimal("1.50");
    private static final BigDecimal SUM_OF_PRICE = new BigDecimal("29.83");
    private static final BigDecimal BOOK_PRICE = new BigDecimal("12.49");
    private static final BigDecimal OTHER_PRICE = new BigDecimal("16.49");
    private static final BigDecimal FOOD_PRICE = new BigDecimal("0.85");

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    @ParameterizedTest
    @MethodSource("invoices")
    void givenInvoiceProduct_shouldReturnReceipt(Invoice invoice) {

        ResponseEntity<Receipt> response = restTemplate.postForEntity(getUrl(), invoice, Receipt.class);
        assertThat(response.getStatusCode()).isEqualTo(OK);

        Receipt receipt = response.getBody();
        assertThat(receipt).isNotNull();
        assertThat(receipt.sumOfTax()).isEqualTo(SUM_OF_TAX);
        assertThat(receipt.sumOfAmount()).isEqualTo(SUM_OF_PRICE);

        Invoice actualInvoice = receipt.invoice();
        assertThat(actualInvoice).isNotNull();
        assertThat(actualInvoice.invoiceDetails()).isNotEmpty().hasSize(3)
            .containsExactly(new InvoiceDetails(1, new Product("Livre", BOOK_PRICE, false, BOOK)),
                new InvoiceDetails(1, new Product("CD musical", OTHER_PRICE, false, OTHER)),
                new InvoiceDetails(1, new Product("Chocolate", FOOD_PRICE, false, FOOD)));
    }

    static Stream<Arguments> invoices() {
        return Stream.of(arguments(oneInvoice()));
    }

    private String getUrl() {
        return "http://localhost:" + port + GENERATE_RECEIPT_URL;
    }
}
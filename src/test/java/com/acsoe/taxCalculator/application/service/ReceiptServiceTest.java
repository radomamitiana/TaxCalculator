package com.acsoe.taxCalculator.application.service;

import com.acsoe.taxCalculator.domain.model.Invoice;
import com.acsoe.taxCalculator.domain.model.InvoiceDetails;
import com.acsoe.taxCalculator.domain.model.Product;
import com.acsoe.taxCalculator.domain.model.Receipt;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.stream.Stream;

import static com.acsoe.taxCalculator.domain.model.ProductCategory.FOOD;
import static com.acsoe.taxCalculator.domain.model.ProductCategory.MEDICAL;
import static com.acsoe.taxCalculator.domain.model.ProductCategory.OTHER;
import static com.acsoe.taxCalculator.domain.sample.InvoiceSample.oneCustomInvoice;
import static java.util.Collections.emptyList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

@ExtendWith(MockitoExtension.class)
class ReceiptServiceTest {

    private static final BigDecimal SUM_OF_TAX = new BigDecimal("6.70");
    private static final BigDecimal SUM_OF_PRICE = new BigDecimal("74.68");

    @InjectMocks
    private ReceiptService receiptService;

    @ParameterizedTest
    @MethodSource("invoices")
    void givenInvoice_shouldReturnReceipt(Invoice invoice) {

        Receipt receipt = receiptService.generateReceipt(invoice);

        assertThat(receipt).isNotNull();
        assertThat(receipt.sumOfTax()).isEqualTo(SUM_OF_TAX);
        assertThat(receipt.sumOfAmount()).isEqualTo(SUM_OF_PRICE);

        Invoice actualInvoice = receipt.invoice();
        assertThat(actualInvoice).isNotNull();
        assertThat(actualInvoice.invoiceDetails()).isNotEmpty().hasSize(4)
            .containsExactly(new InvoiceDetails(1, new Product("Pilules", new BigDecimal("9.75"), false, MEDICAL)),
                new InvoiceDetails(1, new Product("Chocolate", new BigDecimal("11.85"), true, FOOD)),
                new InvoiceDetails(1, new Product("Parfum", new BigDecimal("20.89"), false, OTHER)),
                new InvoiceDetails(1, new Product("Parfum", new BigDecimal("32.19"), true, OTHER)));
    }

    @Test
    void givenInvoiceWithoutInvoiceDetails_shouldReturnNull() {
        Receipt receipt = receiptService.generateReceipt(new Invoice(emptyList()));

        assertThat(receipt).isNull();
    }

    static Stream<Arguments> invoices() {
        return Stream.of(arguments(oneCustomInvoice()));
    }

}
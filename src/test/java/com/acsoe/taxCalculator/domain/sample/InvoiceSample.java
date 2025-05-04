package com.acsoe.taxCalculator.domain.sample;

import com.acsoe.taxCalculator.domain.model.Invoice;
import com.acsoe.taxCalculator.domain.model.Product;

import java.math.BigDecimal;
import java.util.List;

import static com.acsoe.taxCalculator.domain.model.ProductCategory.BOOK;
import static com.acsoe.taxCalculator.domain.model.ProductCategory.FOOD;
import static com.acsoe.taxCalculator.domain.model.ProductCategory.OTHER;
import static com.acsoe.taxCalculator.domain.sample.InvoiceDetailsSample.oneInvoiceDetails;
import static com.acsoe.taxCalculator.domain.sample.ProductSample.oneFoodImportedProduct;
import static com.acsoe.taxCalculator.domain.sample.ProductSample.oneImportedProduct;
import static com.acsoe.taxCalculator.domain.sample.ProductSample.oneMedicalProduct;
import static com.acsoe.taxCalculator.domain.sample.ProductSample.oneProduct;

public final class InvoiceSample {

    private InvoiceSample() {
    }

    public static Invoice oneCustomInvoice() {
        return new Invoice(List.of(oneInvoiceDetails(oneMedicalProduct()), oneInvoiceDetails(oneFoodImportedProduct()), oneInvoiceDetails(oneProduct()),
            oneInvoiceDetails(oneImportedProduct())));
    }

    public static Invoice oneInvoice() {
        return new Invoice(List.of(oneInvoiceDetails(new Product("Livre", new BigDecimal("12.49"), false, BOOK)),
            oneInvoiceDetails(new Product("CD musical", new BigDecimal("14.99"), false, OTHER)),
            oneInvoiceDetails(new Product("Chocolate", new BigDecimal("0.85"), false, FOOD))));
    }
}

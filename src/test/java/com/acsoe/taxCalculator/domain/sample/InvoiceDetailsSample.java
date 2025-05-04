package com.acsoe.taxCalculator.domain.sample;

import com.acsoe.taxCalculator.domain.model.InvoiceDetails;
import com.acsoe.taxCalculator.domain.model.Product;

public final class InvoiceDetailsSample {

    private InvoiceDetailsSample() {
    }

    public static InvoiceDetails oneInvoiceDetails(Product product) {
        return new InvoiceDetails(1, product);
    }
}

package com.acsoe.taxCalculator.application.service;

import com.acsoe.taxCalculator.domain.model.Invoice;
import com.acsoe.taxCalculator.domain.model.InvoiceDetails;
import com.acsoe.taxCalculator.domain.model.Product;
import com.acsoe.taxCalculator.domain.model.Receipt;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static com.acsoe.taxCalculator.domain.util.TaxCalculatorService.calculateTax;
import static java.math.BigDecimal.ZERO;
import static java.math.RoundingMode.HALF_UP;
import static org.springframework.util.CollectionUtils.isEmpty;

@Service
public class ReceiptService {

    public Receipt generateReceipt(Invoice invoice) {

        if (isEmpty(invoice.invoiceDetails())) {
            return null;
        }

        BigDecimal sumOfTax = ZERO;
        BigDecimal sumOfPrice = ZERO;
        List<InvoiceDetails> newInvoiceDetails = new ArrayList<>();

        for (InvoiceDetails details : invoice.invoiceDetails()) {
            Product product = details.product();
            BigDecimal quantity = BigDecimal.valueOf(details.quantity());

            BigDecimal tax = quantity.multiply(calculateTax(product));
            BigDecimal price = quantity.multiply(calculatePrice(product, tax));

            newInvoiceDetails.add(new InvoiceDetails(
                details.quantity(),
                new Product(product.name(), price, product.isImported(), product.category())
            ));

            sumOfTax = sumOfTax.add(tax);
            sumOfPrice = sumOfPrice.add(price);
        }

        return new Receipt(
            new Invoice(newInvoiceDetails),
            sumOfTax.setScale(2, HALF_UP),
            sumOfPrice.setScale(2, HALF_UP)
        );
    }

    private BigDecimal calculatePrice(Product product, BigDecimal tax) {
        return product.price().add(tax);
    }
}

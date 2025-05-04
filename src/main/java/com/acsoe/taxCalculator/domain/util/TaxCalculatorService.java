package com.acsoe.taxCalculator.domain.util;

import com.acsoe.taxCalculator.domain.model.Product;
import com.acsoe.taxCalculator.domain.model.ProductCategory;

import java.math.BigDecimal;

import static com.acsoe.taxCalculator.domain.util.BigDecimalService.roundTax;

public final class TaxCalculatorService {

    private static final BigDecimal STANDARD_TAX_RATE = new BigDecimal("0.10");
    private static final BigDecimal IMPORT_TAX_RATE = new BigDecimal("0.05");

    private TaxCalculatorService() {
    }

    public static BigDecimal calculateTax(Product product) {
        BigDecimal tax = BigDecimal.ZERO;

        if (product == null) {
            return tax;
        }

        if (!isExemptCategory(product)) {
            tax = tax.add(product.price().multiply(STANDARD_TAX_RATE));
        }

        if (product.isImported()) {
            tax = tax.add(product.price().multiply(IMPORT_TAX_RATE));
        }

        return roundTax(tax);
    }

    private static boolean isExemptCategory(Product product) {
        return product.category() == ProductCategory.BOOK
            || product.category() == ProductCategory.FOOD
            || product.category() == ProductCategory.MEDICAL;
    }
}

package com.acsoe.taxCalculator.domain.sample;

import com.acsoe.taxCalculator.domain.model.Product;

import java.math.BigDecimal;

import static com.acsoe.taxCalculator.domain.model.ProductCategory.BOOK;
import static com.acsoe.taxCalculator.domain.model.ProductCategory.FOOD;
import static com.acsoe.taxCalculator.domain.model.ProductCategory.MEDICAL;
import static com.acsoe.taxCalculator.domain.model.ProductCategory.OTHER;

public final class ProductSample {

    private ProductSample() {
    }

    public static Product oneBookProduct() {
        return new Product("Book", new BigDecimal("12.49"), false, BOOK);
    }

    public static Product oneImportedBookProduct() {
        return new Product("Book", new BigDecimal("12.49"), true, BOOK);
    }

    public static Product oneFoodProduct() {
        return new Product("Chocolate", BigDecimal.TEN, false, FOOD);
    }

    public static Product oneFoodImportedProduct() {
        return new Product("Chocolate", new BigDecimal("11.25"), true, FOOD);
    }

    public static Product oneMedicalProduct() {
        return new Product("Pilules", new BigDecimal("9.75"), false, MEDICAL);
    }

    public static Product oneMedicalImportedProduct() {
        return new Product("Antidote", new BigDecimal("18.99"), false, MEDICAL);
    }

    public static Product oneProduct() {
        return new Product("Parfum", new BigDecimal("18.99"), false, OTHER);
    }

    public static Product oneImportedProduct() {
        return new Product("Parfum", new BigDecimal("27.99"), true, OTHER);
    }

}

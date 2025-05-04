package com.acsoe.taxCalculator.domain.util;

import com.acsoe.taxCalculator.domain.model.Product;
import com.acsoe.taxCalculator.domain.model.ProductCategory;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.stream.Stream;

import static com.acsoe.taxCalculator.domain.model.ProductCategory.BOOK;
import static com.acsoe.taxCalculator.domain.model.ProductCategory.FOOD;
import static com.acsoe.taxCalculator.domain.model.ProductCategory.MEDICAL;
import static com.acsoe.taxCalculator.domain.model.ProductCategory.OTHER;
import static com.acsoe.taxCalculator.domain.util.TaxCalculatorService.calculateTax;
import static com.acsoe.taxCalculator.domain.sample.ProductSample.oneBookProduct;
import static com.acsoe.taxCalculator.domain.sample.ProductSample.oneFoodImportedProduct;
import static com.acsoe.taxCalculator.domain.sample.ProductSample.oneFoodProduct;
import static com.acsoe.taxCalculator.domain.sample.ProductSample.oneImportedBookProduct;
import static com.acsoe.taxCalculator.domain.sample.ProductSample.oneImportedProduct;
import static com.acsoe.taxCalculator.domain.sample.ProductSample.oneMedicalImportedProduct;
import static com.acsoe.taxCalculator.domain.sample.ProductSample.oneMedicalProduct;
import static com.acsoe.taxCalculator.domain.sample.ProductSample.oneProduct;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

@ExtendWith(MockitoExtension.class)
class TaxCalculatorServiceTest {

    @ParameterizedTest(name = "{1} && {2} = {3}")
    @MethodSource("products_imported_productCategory_expectedTax")
    void givenProduct_shouldCalculateTax(Product product, Boolean isImported, ProductCategory productCategory, BigDecimal expectedTax) {
        BigDecimal actual = calculateTax(product);

        assertThat(actual).isNotNegative().isEqualTo(expectedTax);
    }

    static Stream<Arguments> products_imported_productCategory_expectedTax() {
        return Stream.of(arguments(oneBookProduct(), false, BOOK, new BigDecimal("0.00")),
            arguments(oneImportedBookProduct(), true, BOOK, new BigDecimal("0.65")),
            arguments(oneFoodProduct(), false, FOOD, new BigDecimal("0.00")),
            arguments(oneFoodImportedProduct(), true, FOOD, new BigDecimal("0.60")),
            arguments(oneMedicalProduct(), false, MEDICAL, new BigDecimal("0.00")),
            arguments(oneMedicalImportedProduct(), true, MEDICAL, new BigDecimal("0.00")),
            arguments(oneProduct(), false, OTHER, new BigDecimal("1.90")),
            arguments(oneImportedProduct(), true, OTHER, new BigDecimal("4.20")),
            arguments(null, false, OTHER, BigDecimal.ZERO));
    }
}
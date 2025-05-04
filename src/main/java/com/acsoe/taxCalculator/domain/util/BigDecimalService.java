package com.acsoe.taxCalculator.domain.util;

import java.math.BigDecimal;

import static java.math.RoundingMode.HALF_UP;
import static java.math.RoundingMode.UP;

public final class BigDecimalService {

    private static final BigDecimal ROUNDING_INCREMENT = new BigDecimal("0.05");

    private BigDecimalService() {
    }

    public static BigDecimal roundTax(BigDecimal tax) {
        BigDecimal divided = tax.divide(ROUNDING_INCREMENT, 0, UP);
        return divided.multiply(ROUNDING_INCREMENT).setScale(2, HALF_UP);
    }
}

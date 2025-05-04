package com.acsoe.taxCalculator.domain.util;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.stream.Stream;

import static com.acsoe.taxCalculator.domain.util.BigDecimalService.roundTax;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

@ExtendWith(MockitoExtension.class)
class BigDecimalServiceTest {

    @ParameterizedTest
    @MethodSource("input_expectedResult")
    void givenBigDecimal_shouldReturnRoundedBigDecimal(BigDecimal input, BigDecimal expectedValue) {
        BigDecimal actual = roundTax(input);

        assertThat(actual).isNotNegative().isEqualTo(expectedValue);
    }

    static Stream<Arguments> input_expectedResult() {
        return Stream.of(arguments(new BigDecimal("0.99"), new BigDecimal("1.00")),
            arguments(new BigDecimal("1.00"), new BigDecimal("1.00")),
            arguments(new BigDecimal("1.01"), new BigDecimal("1.05")),
            arguments(new BigDecimal("1.02"), new BigDecimal("1.05")));
    }
}
package com.acsoe.taxCalculator.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public record Receipt(
    @JsonProperty("invoice")
    Invoice invoice,
    @JsonProperty("sumOfTax")
    BigDecimal sumOfTax,
    @JsonProperty("sumOfAmount")
    BigDecimal sumOfAmount
) {
}

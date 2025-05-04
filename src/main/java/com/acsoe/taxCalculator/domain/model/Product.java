package com.acsoe.taxCalculator.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record Product(
    @NotNull
    @JsonProperty("name")
    String name,
    @NotNull
    @JsonProperty("price")
    BigDecimal price,
    @JsonProperty("isImported")
    boolean isImported,
    @JsonProperty("category")
    ProductCategory category
) {
}

package com.acsoe.taxCalculator.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record InvoiceDetails(
    @NotNull
    @Min(0)
    @JsonProperty("quantity")
    int quantity,
    @NotNull
    @Valid
    @JsonProperty("product")
    Product product) {
}

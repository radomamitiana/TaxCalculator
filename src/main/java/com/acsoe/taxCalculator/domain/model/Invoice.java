package com.acsoe.taxCalculator.domain.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

public record Invoice(
    @JsonInclude(NON_EMPTY)
    @JsonProperty("invoicesDetails")
    List<InvoiceDetails> invoiceDetails) {
}

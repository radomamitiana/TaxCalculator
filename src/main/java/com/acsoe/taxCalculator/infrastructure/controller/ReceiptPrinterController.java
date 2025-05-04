package com.acsoe.taxCalculator.infrastructure.controller;

import com.acsoe.taxCalculator.domain.model.Invoice;
import com.acsoe.taxCalculator.domain.model.Receipt;
import com.acsoe.taxCalculator.application.service.ReceiptService;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/receipts")
public class ReceiptPrinterController {

    private final ReceiptService receiptService;

    public ReceiptPrinterController(ReceiptService receiptService) {
        this.receiptService = receiptService;
    }

    @PostMapping
    public ResponseEntity<Receipt> generateReceipt(@RequestBody @Valid Invoice invoice) {
        Receipt receipt = receiptService.generateReceipt(invoice);
        return ResponseEntity.ok(receipt);
    }
}

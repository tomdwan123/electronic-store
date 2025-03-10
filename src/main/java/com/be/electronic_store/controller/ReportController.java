package com.be.electronic_store.controller;

import com.be.electronic_store.constant.RequestMappingConstant;
import com.be.electronic_store.model.ReceiptProductDTO;
import com.be.electronic_store.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(RequestMappingConstant.REPORT_PATH)
@RequiredArgsConstructor
public class ReportController {

    private final ReportService service;

    @GetMapping("/receipt-products/{customerId}")
    public ResponseEntity<ReceiptProductDTO> getReceiptProducts(@PathVariable Long customerId,
                                                                @RequestParam Long userId) {
        return new ResponseEntity<>(service.getReceiptProducts(customerId, userId), HttpStatus.OK);
    }
}
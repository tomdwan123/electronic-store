package com.be.electronic_store.controller;

import com.be.electronic_store.constant.RequestMappingConstant;
import com.be.electronic_store.model.ReceiptProductDTO;
import com.be.electronic_store.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(RequestMappingConstant.REPORT_PATH)
@RequiredArgsConstructor
// todo: ROLE_CUSTOMER
public class ReportController {

    private final ReportService service;

    @GetMapping("/receipt-products/{userId}")
    public ResponseEntity<ReceiptProductDTO> getReceiptProducts(@PathVariable Long userId) {
        return new ResponseEntity<>(service.getReceiptProducts(userId), HttpStatus.OK);
    }
}
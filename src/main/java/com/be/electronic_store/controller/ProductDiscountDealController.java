package com.be.electronic_store.controller;

import com.be.electronic_store.constant.RequestMappingConstant;
import com.be.electronic_store.model.ProductDiscountDealDTO;
import com.be.electronic_store.service.ProductDiscountDealService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(RequestMappingConstant.PRODUCT_DISCOUNT_DEAL_PATH)
@RequiredArgsConstructor
public class ProductDiscountDealController {

    private final ProductDiscountDealService service;

    @PostMapping
    public ResponseEntity<ProductDiscountDealDTO> addDiscountDealToProduct(@RequestBody ProductDiscountDealDTO productDiscountDealDTO) {
        return new ResponseEntity<>(service.addProductDiscountDeal(productDiscountDealDTO), HttpStatus.CREATED);
    }
}
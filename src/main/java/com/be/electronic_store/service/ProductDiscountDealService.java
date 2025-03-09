package com.be.electronic_store.service;

import com.be.electronic_store.model.ProductDiscountDealDTO;

public interface ProductDiscountDealService {

    ProductDiscountDealDTO addProductDiscountDeal(long userId, ProductDiscountDealDTO productDiscountDealDTO);
}
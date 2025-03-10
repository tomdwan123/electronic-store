package com.be.electronic_store.mockdata;

import com.be.electronic_store.model.ProductDiscountDealDTO;

public class ProductDiscountDealMockData {

    public static ProductDiscountDealDTO getProductDiscountDeal() {

        return ProductDiscountDealDTO
                .builder()
                    .productId(1L)
                    .discountDealId(1L)
                .build();
    }
}
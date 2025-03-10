package com.be.electronic_store.mockdata;

import com.be.electronic_store.model.ReceiptProductDTO;

import java.util.Set;

public class ReceiptProductMockData {

    public static ReceiptProductDTO getReceiptProduct() {

        return ReceiptProductDTO
                .builder()
                    .totalPrice(200.00)
                    .baskets(Set.of())
                .build();
    }
}
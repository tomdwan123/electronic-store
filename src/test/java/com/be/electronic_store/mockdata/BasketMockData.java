package com.be.electronic_store.mockdata;

import com.be.electronic_store.model.BasketDTO;

import java.util.Collections;
import java.util.List;

public class BasketMockData {

    public static BasketDTO getBasket() {

        return BasketDTO
                .builder()
                    .userId(1L)
                    .productId(2L)
                    .quantity(2)
                .build();
    }

    public static List<BasketDTO> getBaskets() {
        return Collections.singletonList(getBasket());
    }
}
package com.be.electronic_store.service;

import com.be.electronic_store.model.BasketDTO;

import java.util.List;

public interface BasketService {

    List<BasketDTO> getBasketsByUserId(Long userId);

    BasketDTO addProductToBasket(BasketDTO basketDTO);

    void deleteProductFromBasket(BasketDTO basketDTO);
}
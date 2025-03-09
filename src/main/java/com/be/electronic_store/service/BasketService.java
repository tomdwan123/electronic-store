package com.be.electronic_store.service;

import com.be.electronic_store.model.BasketDTO;

import java.util.List;

public interface BasketService {

    List<BasketDTO> getBasketsByUserId(long userId);

    BasketDTO addProductToBasket(long userId, BasketDTO basketDTO);

    void deleteProductFromBasket(long userId, BasketDTO basketDTO);
}
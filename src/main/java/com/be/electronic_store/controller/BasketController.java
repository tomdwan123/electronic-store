package com.be.electronic_store.controller;

import com.be.electronic_store.constant.RequestMappingConstant;
import com.be.electronic_store.model.BasketDTO;
import com.be.electronic_store.service.BasketService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(RequestMappingConstant.PRODUCT_BASKET_PATH)
@RequiredArgsConstructor
public class BasketController {

    private final BasketService service;

    @GetMapping
    public ResponseEntity<List<BasketDTO>> getBaskets(@RequestParam long userId) {
        return new ResponseEntity<>(service.getBasketsByUserId(userId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<BasketDTO> addProductToBasket(@RequestParam long userId,
                                                        @Valid @RequestBody BasketDTO basketDTO) {
        return new ResponseEntity<>(service.addProductToBasket(userId, basketDTO), HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteProductFromBasket(@RequestParam long userId,
                                                        @Valid @RequestBody BasketDTO basketDTO) {
        service.deleteProductFromBasket(userId, basketDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
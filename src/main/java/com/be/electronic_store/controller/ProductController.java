package com.be.electronic_store.controller;

import com.be.electronic_store.constant.RequestMappingConstant;
import com.be.electronic_store.model.PageableResponseModel;
import com.be.electronic_store.model.ProductDTO;
import com.be.electronic_store.service.ProductService;
import com.be.electronic_store.util.PageResponseUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(RequestMappingConstant.PRODUCT_PATH)
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @RequestMapping()
    public ResponseEntity<PageableResponseModel<ProductDTO>> getProducts() {
        return PageResponseUtils.get(productService.getProducts());
    }
}

package com.be.electronic_store.controller;

import com.be.electronic_store.constant.RequestMappingConstant;
import com.be.electronic_store.model.PageableResponseModel;
import com.be.electronic_store.model.ProductDTO;
import com.be.electronic_store.service.ProductService;
import com.be.electronic_store.util.PageResponseUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(RequestMappingConstant.PRODUCT_PATH)
@RequiredArgsConstructor
public class ProductController {

    private final ProductService service;

    @RequestMapping()
    public ResponseEntity<PageableResponseModel<ProductDTO>> getProducts() {
        return PageResponseUtils.get(service.getProducts());
    }

    @PostMapping()
    public ResponseEntity<ProductDTO> addProduct(@RequestBody ProductDTO productDTO) {
        return new ResponseEntity<>(service.addProduct(productDTO), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        service.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

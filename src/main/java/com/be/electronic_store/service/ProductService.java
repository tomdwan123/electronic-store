package com.be.electronic_store.service;

import com.be.electronic_store.model.ProductDTO;
import org.springframework.data.domain.Page;

public interface ProductService {

    Page<ProductDTO> getProducts(long userId);

    ProductDTO addProduct(long userId, ProductDTO productDTO);

    void deleteProduct(long productId, long userId);
}

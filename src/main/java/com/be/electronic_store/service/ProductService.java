package com.be.electronic_store.service;

import com.be.electronic_store.model.ProductDTO;
import org.springframework.data.domain.Page;

public interface ProductService {

    Page<ProductDTO> getProducts();
}

package com.be.electronic_store.mockdata;

import com.be.electronic_store.constant.CommonConstant;
import com.be.electronic_store.model.ProductDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Collections;
import java.util.List;

public class ProductMockData {

    public static ProductDTO getProduct() {

        return ProductDTO
                .builder()
                    .id(1L)
                    .name("Product 1")
                    .price(1000.00)
                .build();
    }

    private static List<ProductDTO> getProductDTOs() {
        
        return Collections.singletonList(
                getProduct()
        );
    }

    private static Pageable getPageable(int pageSize) {
        return PageRequest.of(CommonConstant.PAGE_SIZE_DEFAULT, pageSize);
    }

    public static Page<ProductDTO> getProducts() {

        List<ProductDTO> products = getProductDTOs();
        Pageable pageable = getPageable(products.size());
        return new PageImpl<>(products, pageable, products.size());
    }
}
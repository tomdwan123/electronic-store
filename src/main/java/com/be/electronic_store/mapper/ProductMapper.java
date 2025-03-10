package com.be.electronic_store.mapper;

import com.be.electronic_store.entity.Product;
import com.be.electronic_store.model.ProductDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductDTO toDto(Product product);

    @Mapping(target = "version", ignore = true)
    Product toEntity(ProductDTO productDTO);
}
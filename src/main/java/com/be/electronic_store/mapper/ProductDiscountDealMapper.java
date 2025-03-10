package com.be.electronic_store.mapper;

import com.be.electronic_store.entity.ProductDiscountDeal;
import com.be.electronic_store.model.ProductDiscountDealDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductDiscountDealMapper {

    @Mapping(target = "productId", source = "productDiscountDealIdKey.productId")
    @Mapping(target = "discountDealId", source = "productDiscountDealIdKey.discountDealId")
    ProductDiscountDealDTO toDto(ProductDiscountDeal productDiscountDeal);

    @Mapping(target = "version", ignore = true)
    ProductDiscountDeal toEntity(ProductDiscountDealDTO productDiscountDealDTO);
}
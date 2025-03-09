package com.be.electronic_store.mapper;

import com.be.electronic_store.entity.ProductDiscountDeal;
import com.be.electronic_store.model.ProductDiscountDealDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductDiscountDealMapper {

    ProductDiscountDealDTO toDto(ProductDiscountDeal productDiscountDeal);

    ProductDiscountDeal toEntity(ProductDiscountDealDTO productDiscountDealDTO);
}
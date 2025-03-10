package com.be.electronic_store.mapper;

import com.be.electronic_store.entity.Basket;
import com.be.electronic_store.model.BasketDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BasketMapper {

    @Mapping(target = "userId", source = "basketIdKey.userId")
    @Mapping(target = "productId", source = "basketIdKey.productId")
    BasketDTO toDTO(Basket basket);

    @Mapping(target = "version", ignore = true)
    Basket toEntity(BasketDTO basketDTO);
}
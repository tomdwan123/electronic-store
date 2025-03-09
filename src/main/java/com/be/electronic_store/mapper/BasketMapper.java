package com.be.electronic_store.mapper;

import com.be.electronic_store.entity.Basket;
import com.be.electronic_store.model.BasketDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BasketMapper {

    BasketDTO toDTO(Basket basket);

    Basket toEntity(BasketDTO basketDTO);
}
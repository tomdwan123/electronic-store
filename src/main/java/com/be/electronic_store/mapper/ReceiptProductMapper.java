package com.be.electronic_store.mapper;

import com.be.electronic_store.entity.User;
import com.be.electronic_store.model.ReceiptProductDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface ReceiptProductMapper {

    @Mapping(target = "totalPrice", expression = "java(getTotalPrice(user))")
    ReceiptProductDTO toModel(User user);

    @Named("getTotalPrice")
    default double getTotalPrice(User user) {

        return user.getBaskets().stream()
                .mapToDouble(basket -> basket.getQuantity() * basket.getProduct().getPrice())
                .sum();
    }
}
package com.be.electronic_store.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "products")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Product extends BaseEntity {

    String name;

    double price;

    @NotNull
    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    Set<ProductDiscountDeal> productDiscountDeals;

    @NotNull
    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    Set<Basket> baskets;
}

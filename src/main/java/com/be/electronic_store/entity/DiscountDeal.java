package com.be.electronic_store.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
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
@Table(name = "discount_deals")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DiscountDeal extends BaseEntity {

    String description;

    @OneToMany(mappedBy = "discountDeal", fetch = FetchType.LAZY)
    Set<ProductDiscountDeal> productDiscountDeals;
}
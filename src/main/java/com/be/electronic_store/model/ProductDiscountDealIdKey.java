package com.be.electronic_store.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Embeddable
public class ProductDiscountDealIdKey implements Serializable {

    static final long serialVersionUID = -4695626897695072686L;

    @NotNull
    @Column(name = "product_id", nullable = false)
    Long productId;

    @NotNull
    @Column(name = "discount_deal_id", nullable = false)
    Long discountDealId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductDiscountDealIdKey that = (ProductDiscountDealIdKey) o;
        return Objects.equals(productId, that.productId) && Objects.equals(discountDealId, that.discountDealId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, discountDealId);
    }
}
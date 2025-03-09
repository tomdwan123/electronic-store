package com.be.electronic_store.repository;

import com.be.electronic_store.entity.ProductDiscountDeal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDiscountDealRepository extends JpaRepository<ProductDiscountDeal, Long> {

    @Query("SELECT pdd FROM ProductDiscountDeal pdd WHERE pdd.product.id = :productId AND pdd.discountDeal.id = :discountDealId")
    ProductDiscountDeal findIdByProductIdAndDiscountDealId(long productId, long discountDealId);
}
package com.be.electronic_store.repository;

import com.be.electronic_store.entity.ProductDiscountDeal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDiscountDealRepository extends JpaRepository<ProductDiscountDeal, Long> {
}
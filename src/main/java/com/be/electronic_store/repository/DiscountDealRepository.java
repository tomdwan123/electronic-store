package com.be.electronic_store.repository;

import com.be.electronic_store.entity.DiscountDeal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiscountDealRepository extends JpaRepository<DiscountDeal, Long> {
}
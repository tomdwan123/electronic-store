package com.be.electronic_store.repository;

import com.be.electronic_store.entity.Basket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BasketRepository extends JpaRepository<Basket, Long> {

    @Query("SELECT b FROM Basket b WHERE b.user.id = :userId")
    List<Basket> findByUserId(long userId);

    @Query("SELECT b FROM Basket  b WHERE b.user.id = :userId AND b.product.id = :productId")
    Basket findIdByUserIdAndProductId(long userId, long productId);
}
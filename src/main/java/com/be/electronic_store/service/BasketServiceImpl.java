package com.be.electronic_store.service;

import com.be.electronic_store.entity.Basket;
import com.be.electronic_store.exception.ExceptionFactory;
import com.be.electronic_store.mapper.BasketMapper;
import com.be.electronic_store.model.BasketDTO;
import com.be.electronic_store.repository.BasketRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@Service
@Slf4j
@RequiredArgsConstructor
public class BasketServiceImpl implements BasketService {

    private final BasketMapper mapper;

    private final BasketRepository repository;

    private final ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();

    @Override
    public List<BasketDTO> getBasketsByUserId(Long userId) {

        rwLock.readLock().lock();
        try {
            return repository.findByUserId(userId).stream()
                    .map(mapper::toDTO)
                    .toList();
        } finally {
            rwLock.readLock().unlock();
        }
    }

    @Override
    public BasketDTO addProductToBasket(BasketDTO basketDTO) {

        rwLock.writeLock().lock();
        try {
            if (Objects.isNull(basketDTO)) {
                throw ExceptionFactory.validationException("basketDTO is null");
            }

            Basket existingBasket = repository.findIdByUserIdAndProductId(basketDTO.getUserId(), basketDTO.getProductId());
            if (Objects.nonNull(existingBasket)) {
                throw ExceptionFactory.conflictException(String.format("Product %s has already purchased by customer %s",
                        existingBasket.getProduct().getName(), existingBasket.getUser().getEmail()));
            }

            Basket basket = repository.save(mapper.toEntity(basketDTO));
            return mapper.toDTO(basket);
        } finally {
            rwLock.writeLock().unlock();
        }
    }

    @Override
    public void deleteProductFromBasket(BasketDTO basketDTO) {

        rwLock.writeLock().lock();
        try {
            if (Objects.isNull(basketDTO)) {
                throw ExceptionFactory.validationException("basketDTO is null");
            }

            Basket existingBasket = repository.findIdByUserIdAndProductId(basketDTO.getUserId(), basketDTO.getProductId());
            if (Objects.isNull(existingBasket)) {
                throw ExceptionFactory.notFoundException(String.format("Product %s ot found in the basket of customer %s", basketDTO.getProductId(), basketDTO.getUserId()));
            }

            repository.delete(existingBasket);
        } finally {
            rwLock.writeLock().unlock();
        }
    }
}
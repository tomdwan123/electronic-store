package com.be.electronic_store.service;

import com.be.electronic_store.entity.ProductDiscountDeal;
import com.be.electronic_store.exception.ExceptionFactory;
import com.be.electronic_store.mapper.ProductDiscountDealMapper;
import com.be.electronic_store.model.ProductDiscountDealDTO;
import com.be.electronic_store.repository.ProductDiscountDealRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductDiscountDealServiceImpl implements ProductDiscountDealService {

    private final ProductDiscountDealMapper mapper;

    private final ProductDiscountDealRepository repository;

    private final ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();

    @Override
    public ProductDiscountDealDTO addProductDiscountDeal(ProductDiscountDealDTO productDiscountDealDTO) {

        rwLock.writeLock().lock();
        try {
            if (Objects.isNull(productDiscountDealDTO)) {
                throw ExceptionFactory.validationException("productDiscountDealDTO is null");
            }

            // todo: set created_by and updated_by -> ROLE_ADMIN
            ProductDiscountDeal productDiscountDeal = repository.save(mapper.toEntity(productDiscountDealDTO));
            return mapper.toDto(productDiscountDeal);
        } finally {
            rwLock.writeLock().unlock();
        }
    }
}
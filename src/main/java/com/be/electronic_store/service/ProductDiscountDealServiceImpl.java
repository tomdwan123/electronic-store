package com.be.electronic_store.service;

import com.be.electronic_store.constant.RoleEnum;
import com.be.electronic_store.entity.DiscountDeal;
import com.be.electronic_store.entity.Product;
import com.be.electronic_store.entity.ProductDiscountDeal;
import com.be.electronic_store.exception.ExceptionFactory;
import com.be.electronic_store.mapper.ProductDiscountDealMapper;
import com.be.electronic_store.model.ProductDiscountDealDTO;
import com.be.electronic_store.model.ProductDiscountDealIdKey;
import com.be.electronic_store.repository.DiscountDealRepository;
import com.be.electronic_store.repository.ProductDiscountDealRepository;
import com.be.electronic_store.repository.ProductRepository;
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

    private final UserService userService;

    private final ProductDiscountDealRepository repository;

    private final ProductRepository productRepository;

    private final DiscountDealRepository discountDealRepository;

    private final ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();

    @Override
    public ProductDiscountDealDTO addProductDiscountDeal(long userId, ProductDiscountDealDTO productDiscountDealDTO) {

        userService.checkPermission(userId, RoleEnum.ADMIN);

        if (Objects.isNull(productDiscountDealDTO)) {
            throw ExceptionFactory.validationException("productDiscountDealDTO is null");
        }

        rwLock.readLock().lock();
        try {
            checkExistProductDiscountDeal(productDiscountDealDTO.getProductId(), productDiscountDealDTO.getDiscountDealId());
        } finally {
            rwLock.readLock().unlock();
        }

        rwLock.writeLock().lock();
        ProductDiscountDeal productDiscountDeal = getProductDiscountDeal(productDiscountDealDTO.getProductId(), productDiscountDealDTO.getDiscountDealId());
        try {
            productDiscountDeal = repository.save(productDiscountDeal);
            return mapper.toDto(productDiscountDeal);
        } finally {
            rwLock.writeLock().unlock();
        }
    }

    void checkExistProductDiscountDeal(long productId, long discountDealId) {

        ProductDiscountDeal existingDiscount = repository.findIdByProductIdAndDiscountDealId(productId, discountDealId);
        if (Objects.nonNull(existingDiscount)) {
            throw ExceptionFactory.conflictException(String.format("Discount %s has already applied to product %s",
                    existingDiscount.getDiscountDeal().getDescription(), existingDiscount.getProduct().getName()));
        }
    }

    ProductDiscountDeal getProductDiscountDeal(long productId, long discountDealId) {

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> ExceptionFactory.notFoundException(String.format("Product not found with id = %s", productId)));

        DiscountDeal discountDeal = discountDealRepository.findById(discountDealId)
                .orElseThrow(() -> ExceptionFactory.notFoundException(String.format("Discount Deal not found with id = %s", discountDealId)));

        ProductDiscountDealIdKey idKey = ProductDiscountDealIdKey
                .builder()
                    .productId(productId)
                    .discountDealId(discountDealId)
                .build();

        return ProductDiscountDeal
                        .builder()
                            .product(product)
                            .discountDeal(discountDeal)
                            .productDiscountDealIdKey(idKey)
                        .build();
    }
}
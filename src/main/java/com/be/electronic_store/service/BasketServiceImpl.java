package com.be.electronic_store.service;

import com.be.electronic_store.constant.RoleEnum;
import com.be.electronic_store.entity.Basket;
import com.be.electronic_store.entity.Product;
import com.be.electronic_store.entity.User;
import com.be.electronic_store.exception.ExceptionFactory;
import com.be.electronic_store.mapper.BasketMapper;
import com.be.electronic_store.model.BasketDTO;
import com.be.electronic_store.model.BasketIdKey;
import com.be.electronic_store.repository.BasketRepository;
import com.be.electronic_store.repository.ProductRepository;
import com.be.electronic_store.repository.UserRepository;
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

    private final UserService userService;

    private final BasketRepository repository;

    private final UserRepository userRepository;

    private final ProductRepository productRepository;

    private final ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();

    @Override
    public List<BasketDTO> getBasketsByUserId(long userId) {

        userService.checkPermission(userId, RoleEnum.CUSTOMER);

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
    public BasketDTO addProductToBasket(long userId, BasketDTO basketDTO) {

        userService.checkPermission(userId, RoleEnum.CUSTOMER);

        if (Objects.isNull(basketDTO)) {
            throw ExceptionFactory.validationException("basketDTO is null");
        }

        rwLock.readLock().lock();
        try {
            checkExistBasket(basketDTO.getUserId(), basketDTO.getProductId());
        } finally {
            rwLock.readLock().unlock();
        }

        rwLock.writeLock().lock();
        Basket basket = getBasket(basketDTO);
        try {
            basket = repository.save(basket);
            return mapper.toDTO(basket);
        } finally {
            rwLock.writeLock().unlock();
        }
    }

    @Override
    public void deleteProductFromBasket(long userId, BasketDTO basketDTO) {

        userService.checkPermission(userId, RoleEnum.CUSTOMER);

        if (Objects.isNull(basketDTO)) {
            throw ExceptionFactory.validationException("basketDTO is null");
        }

        Basket existingBasket = repository.findIdByUserIdAndProductId(basketDTO.getUserId(), basketDTO.getProductId());
        if (Objects.isNull(existingBasket)) {
            throw ExceptionFactory.notFoundException(String.format("Product with id = %s not found in the basket of customer with id = %s", basketDTO.getProductId(), basketDTO.getUserId()));
        }

        rwLock.writeLock().lock();
        try {
            repository.delete(existingBasket);
        } finally {
            rwLock.writeLock().unlock();
        }
    }

    void checkExistBasket(long userId, long productId) {

        Basket existingBasket = repository.findIdByUserIdAndProductId(userId, productId);
        if (Objects.nonNull(existingBasket)) {
            throw ExceptionFactory.conflictException(String.format("Product %s has already purchased by customer %s",
                    existingBasket.getProduct().getName(), existingBasket.getUser().getEmail()));
        }

    }

    Basket getBasket(BasketDTO basketDTO) {

        long userId = basketDTO.getUserId();
        long productId = basketDTO.getProductId();

        User user = userRepository.findById(userId)
                .orElseThrow(() -> ExceptionFactory.notFoundException(String.format("User not found with id = %s", userId)));

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> ExceptionFactory.notFoundException(String.format("Product not found with id = %s", productId)));

        BasketIdKey idKey = BasketIdKey
                .builder()
                    .userId(userId)
                    .productId(productId)
                .build();

        return Basket
                .builder()
                    .user(user)
                    .product(product)
                    .basketIdKey(idKey)
                    .quantity(basketDTO.getQuantity())
                .build();

    }
}
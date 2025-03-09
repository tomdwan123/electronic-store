package com.be.electronic_store.service;

import com.be.electronic_store.constant.CommonConstant;
import com.be.electronic_store.constant.RoleEnum;
import com.be.electronic_store.entity.Product;
import com.be.electronic_store.exception.ExceptionFactory;
import com.be.electronic_store.mapper.ProductMapper;
import com.be.electronic_store.model.ProductDTO;
import com.be.electronic_store.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductMapper mapper;

    private final UserService userService;

    private final ProductRepository repository;

    private final ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();

    @Override
    public Page<ProductDTO> getProducts(long userId) {

        userService.checkPermission(userId, RoleEnum.ADMIN);

        rwLock.readLock().lock();
        try {
            List<ProductDTO> products = repository.findAll().stream()
                    .map(mapper::toDto)
                    .toList();

            int pageSize = products.isEmpty() ? CommonConstant.PAGE_SIZE_DEFAULT : products.size();
            Pageable pageable = PageRequest.of(0, pageSize);
            return new PageImpl<>(products, pageable, products.size());
        } finally {
            rwLock.readLock().unlock();
        }
    }

    @Override
    public ProductDTO addProduct(long userId, ProductDTO productDTO) {

        userService.checkPermission(userId, RoleEnum.ADMIN);

        rwLock.writeLock().lock();
        try {
            if (Objects.isNull(productDTO)) {
                throw ExceptionFactory.validationException("Product DTO should not be null");
            }

            // todo: set created_by and updated_by -> ROLE_ADMIN
            Product product = repository.save(mapper.toEntity(productDTO));
            return mapper.toDto(product);
        } finally {
            rwLock.writeLock().unlock();
        }
    }

    @Override
    public void deleteProduct(long productId, long userId) {

        userService.checkPermission(userId, RoleEnum.ADMIN);

        rwLock.writeLock().lock();
        try {
            repository.findById(productId)
                    .orElseThrow(() -> ExceptionFactory.notFoundException(String.format("Product not found with id: %s", productId)));
            repository.deleteById(productId);
        } finally {
            rwLock.writeLock().unlock();
        }
    }
}

package com.be.electronic_store.service;

import com.be.electronic_store.constant.CommonConstant;
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
import java.util.concurrent.locks.ReentrantReadWriteLock;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductMapper productMapper;

    private final ProductRepository productRepository;

    private final ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();

    @Override
    public Page<ProductDTO> getProducts() {

        rwLock.readLock().lock();
        try {
            List<ProductDTO> products = productRepository.findAll().stream()
                    .map(productMapper::toDto)
                    .toList();

            int pageSize = products.isEmpty() ? CommonConstant.PAGE_SIZE_DEFAULT : products.size();
            Pageable pageable = PageRequest.of(0, pageSize);
            return new PageImpl<>(products, pageable, products.size());
        } finally {
            rwLock.readLock().unlock();
        }
    }
}

package com.be.electronic_store.service;

import com.be.electronic_store.entity.User;
import com.be.electronic_store.exception.ExceptionFactory;
import com.be.electronic_store.mapper.ReceiptProductMapper;
import com.be.electronic_store.model.ReceiptProductDTO;
import com.be.electronic_store.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.locks.ReentrantReadWriteLock;

@Service
@Slf4j
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {

    private final ReceiptProductMapper receiptProductMapper;

    private final UserRepository userRepository;

    private final ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();

    @Override
    public ReceiptProductDTO getReceiptProducts(long userId) {

        rwLock.readLock().lock();
        try {
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> ExceptionFactory.notFoundException(String.format("User with id %s not found", userId)));
            return receiptProductMapper.toModel(user);
        } finally {
            rwLock.readLock().unlock();
        }
    }
}
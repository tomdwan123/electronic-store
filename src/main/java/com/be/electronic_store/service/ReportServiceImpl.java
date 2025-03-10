package com.be.electronic_store.service;

import com.be.electronic_store.constant.RoleEnum;
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

    private final UserService userService;

    private final UserRepository userRepository;

    private final ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();

    @Override
    public ReceiptProductDTO getReceiptProducts(long customerId, long userId) {

        userService.checkPermission(userId, RoleEnum.CUSTOMER);

        rwLock.readLock().lock();
        try {
            User user = userRepository.findById(customerId)
                    .orElseThrow(() -> ExceptionFactory.notFoundException(String.format("Customer with id = %s not found", userId)));
            return receiptProductMapper.toModel(user);
        } finally {
            rwLock.readLock().unlock();
        }
    }
}
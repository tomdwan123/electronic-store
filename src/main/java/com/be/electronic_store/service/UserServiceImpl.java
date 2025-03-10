package com.be.electronic_store.service;

import com.be.electronic_store.constant.RoleEnum;
import com.be.electronic_store.entity.User;
import com.be.electronic_store.exception.ExceptionFactory;
import com.be.electronic_store.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public void checkPermission(Long userId, RoleEnum role) {

        User existUser = userRepository.findByIdAndRole(userId, role);
        if (Objects.isNull(existUser)) {
            throw ExceptionFactory.forbiddenException(String.format("User with id = %s has no permission", userId));
        }
    }
}
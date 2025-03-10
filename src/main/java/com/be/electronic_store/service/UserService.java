package com.be.electronic_store.service;

import com.be.electronic_store.constant.RoleEnum;

public interface UserService {

    void checkPermission(Long userId, RoleEnum role);
}
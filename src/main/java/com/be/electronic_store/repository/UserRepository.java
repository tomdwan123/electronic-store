package com.be.electronic_store.repository;

import com.be.electronic_store.constant.RoleEnum;
import com.be.electronic_store.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u JOIN UserRole ur ON ur.user.id = u.id " +
            "WHERE u.id = :userId AND ur.role.name = :role")
    User findByIdAndRole(Long userId, RoleEnum role);
}
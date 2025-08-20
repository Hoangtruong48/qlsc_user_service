package com.example.quanlysancau.repo;

import com.example.quanlysancau.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface UserRepoJpa extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {
    Boolean existsByUsername(String username);
    Optional<User> findByUsername(String username);
}

package com.alvin.alvinvoucher.repository;

import com.alvin.alvinvoucher.constant.ERole;
import com.alvin.alvinvoucher.entity.UserCredential;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserCredentialRepository extends JpaRepository<UserCredential, String> {
    Optional<UserCredential> findById(String id);
    Optional<UserCredential> findByUsername(String username);
    boolean existsByRole_Name(ERole roleName);
}

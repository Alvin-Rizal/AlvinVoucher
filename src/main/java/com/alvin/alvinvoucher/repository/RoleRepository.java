package com.alvin.alvinvoucher.repository;

import com.alvin.alvinvoucher.constant.ERole;
import com.alvin.alvinvoucher.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository{
    Optional<Role> findByRoleName(ERole name);
    Role save(Role role);
}

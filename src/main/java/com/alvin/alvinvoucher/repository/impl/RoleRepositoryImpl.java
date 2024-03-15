package com.alvin.alvinvoucher.repository.impl;

import com.alvin.alvinvoucher.constant.ERole;
import com.alvin.alvinvoucher.entity.Role;
import com.alvin.alvinvoucher.repository.RoleRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleRepositoryImpl implements RoleRepository {

    @PersistenceContext
    private final EntityManager em;
    @Override
    @Transactional
    public Optional<Role> findByRoleName(ERole name) {
        String jpql = "SELECT r FROM Role r WHERE r.name = :role_name";
        return em.createQuery(jpql, Role.class)
                .setParameter("role_name",name)
                .getResultList()
                .stream().findFirst();
    }

    @Override
    @Transactional
    public Role save(Role role) {
        String query = "INSERT INTO Role (name) VALUES (?)";
        em.createNativeQuery(query)
                .setParameter(1,role.getName())
                .executeUpdate();
        return em.find(Role.class, role.getId());
    }
}

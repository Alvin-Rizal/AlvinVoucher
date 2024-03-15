package com.alvin.alvinvoucher.repository.impl;

import com.alvin.alvinvoucher.entity.Customer;
import com.alvin.alvinvoucher.repository.CustomerRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerRepositoryImpl implements CustomerRepository {

    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    @Transactional
    public Customer saveAndFlush(Customer customer) {
        String queryInsert = "Insert into m_customer (name,email,phone) values (?, ?, ?)";
        entityManager.createNativeQuery(queryInsert, Customer.class)
                .setParameter(1,customer.getName())
                .setParameter(2,customer.getEmail())
                .setParameter(3,customer.getPhone())
                .getResultList();

        entityManager.flush();
        return entityManager.find(Customer.class,customer.getId());
    }

    @Override
    @Transactional
    public List<Customer> findAll(Customer customer) {
        String querySelect = "select * from m_customer";
        return entityManager.createNativeQuery(querySelect, Customer.class)
                .getResultList();

    }

    @Override
    @Transactional
    public Customer findById(String id) {
        String querySelect = "select * from m_customer where id = ?";
        entityManager.createNativeQuery(querySelect)
                .setParameter(1,id)
                .getResultList().stream().findFirst();
        return entityManager.find(Customer.class,id);
    }

    @Override
    @Transactional
    public Customer findByUserCredentials(String id) {
        String querySelect = "select * from m_user_credential where id = ?";
        entityManager.createNativeQuery(querySelect)
                .setParameter(1,id)
                .getResultList().stream().findFirst();
        return entityManager.find(Customer.class,id);
    }
}

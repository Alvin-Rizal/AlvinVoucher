package com.alvin.alvinvoucher.repository;

import com.alvin.alvinvoucher.entity.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository {
    Customer saveAndFlush(Customer customer);
    List<Customer> findAll(Customer customer);
    Customer findById(String id);
    Customer findByUserCredentials(String id);
}

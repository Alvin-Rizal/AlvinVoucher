package com.alvin.alvinvoucher.service;

import com.alvin.alvinvoucher.dto.request.AuthRequest;
import com.alvin.alvinvoucher.dto.response.CustomerResponse;
import com.alvin.alvinvoucher.entity.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    CustomerResponse create(Customer customer);
    CustomerResponse update(AuthRequest customer);
    CustomerResponse delete(String id);
    CustomerResponse getCustomerById(String id);
    List<CustomerResponse> getAllCustomers();

    Optional<Customer> getCustomerByUserCredentialId(String id);
}

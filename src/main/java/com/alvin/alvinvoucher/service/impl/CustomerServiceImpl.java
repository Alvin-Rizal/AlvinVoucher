package com.alvin.alvinvoucher.service.impl;

import com.alvin.alvinvoucher.dto.request.AuthRequest;
import com.alvin.alvinvoucher.dto.response.CustomerResponse;
import com.alvin.alvinvoucher.entity.Customer;
import com.alvin.alvinvoucher.entity.UserCredential;
import com.alvin.alvinvoucher.repository.CustomerRepository;
import com.alvin.alvinvoucher.service.CustomerService;
import com.alvin.alvinvoucher.service.UserCredentialService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final UserCredentialService userCredentialService;

    @Override
    public CustomerResponse create(Customer customer) {
        Customer customer1 = customerRepository.saveAndFlush(customer);
        return CustomerResponse.builder()
                .id(customer1.getId())
                .name(customer1.getName())
                .emailCustomer(customer1.getEmail())
                .phoneCustomer(customer1.getPhone())
                .build();
    }

    @Override
    public CustomerResponse update(AuthRequest customer) {
        Customer customer1 = customerRepository.findById(customer.getId());
        UserCredential userCredential = UserCredential.builder()
                .id(customer1.getUserCredential().getId())
                .password(customer1.getUserCredential().getPassword())
                .username(customer1.getUserCredential().getUsername())
                .role(customer1.getUserCredential().getRole()).build();
        userCredentialService.updateUserCredential(userCredential);
        Customer customer2 = Customer.builder()
                .id(customer.getId())
                .email(customer.getEmail())
                .phone(customer.getPhone())
                .build();
        customerRepository.saveAndFlush(customer2);
        return CustomerResponse.builder()
                .name(customer2.getName())
                .phoneCustomer(customer2.getPhone())
                .emailCustomer(customer2.getPhone()).build();
    }

    @Override
    public CustomerResponse delete(String id) {
        return null;
    }

    @Override
    public CustomerResponse getCustomerById(String id) {
        return null;
    }

    @Override
    public List<CustomerResponse> getAllCustomers() {
        return List.of();
    }

    @Override
    public Optional<Customer> getCustomerByUserCredentialId(String id) {
        return Optional.empty();
    }
}

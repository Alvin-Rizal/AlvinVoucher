package com.alvin.alvinvoucher.mapper;

import com.alvin.alvinvoucher.constant.ERole;
import com.alvin.alvinvoucher.dto.request.AuthRequest;
import com.alvin.alvinvoucher.dto.response.LoginResponse;
import com.alvin.alvinvoucher.dto.response.RegisterResponse;
import com.alvin.alvinvoucher.entity.Customer;
import com.alvin.alvinvoucher.entity.UserCredential;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

public class CustomerMapper {

    public static Customer mapToCustomer(UserCredential userCredential, AuthRequest registerRequest){
        return Customer.builder()
                .userCredential(userCredential)
                .name(registerRequest.getName())
                .phone(registerRequest.getPhone())
                .email(registerRequest.getEmail())
                .build();
    }

    public static RegisterResponse mapToRegisterCustomer(UserCredential userCredential, AuthRequest registerRequest){
        return RegisterResponse.builder()
                .username(userCredential.getUsername())
                .role(userCredential.getRole().getName().toString())
                .build();
    }

    public static LoginResponse mapToLoginResponse(Optional<Customer> customerOpt, ERole role, String token) {
        Customer customer = customerOpt.orElseThrow(() -> new IllegalArgumentException("Customer not found"));
        return LoginResponse.builder()
                .username(customer.getName())
                .token(token)
                .role(role.name())
                .build();
    }
}

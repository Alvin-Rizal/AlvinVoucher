package com.alvin.alvinvoucher.controller;

import com.alvin.alvinvoucher.dto.request.AuthRequest;
import com.alvin.alvinvoucher.dto.response.CustomerResponse;
import com.alvin.alvinvoucher.dto.response.DefaultResponse;
import com.alvin.alvinvoucher.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService customerService;
    @GetMapping
    public ResponseEntity<?> getAllCustomer() {
        List<CustomerResponse> customerList = customerService.getAllCustomers();
        return ResponseEntity.status(HttpStatus.OK)
                .body(DefaultResponse.builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Fetch Customer List Success")
                        .data(customerList)
                        .build());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getCustomerById(@PathVariable String id) {
        CustomerResponse customer = customerService.getCustomerById(id);
        if (customer != null) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(DefaultResponse.builder()
                            .statusCode(HttpStatus.OK.value())
                            .message("Fetch Success")
                            .data(customer)
                            .build());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(DefaultResponse.builder()
                        .statusCode(HttpStatus.NOT_FOUND.value())
                        .message("Data Not Found")
                        .build());
    }

    @PutMapping
    public ResponseEntity<?> updateCustomer( @RequestParam String name ,@RequestParam String username, @RequestParam String phoneNumber , @RequestParam String email , @RequestParam String password) {

        AuthRequest registerRequest = AuthRequest.builder()
                .email(email)
                .password(password)
                .name(name)
                .phone(phoneNumber)
                .username(username)
                .build();

        CustomerResponse customer = customerService.update(registerRequest);

        if (registerRequest != null) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(DefaultResponse.builder()
                            .statusCode(HttpStatus.OK.value())
                            .message("Update Success")
//                            .data(registerRequest)
                            .build());
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(DefaultResponse.builder()
                        .statusCode(HttpStatus.NOT_FOUND.value())
                        .message("Update Failed")
                        .data(customer)
                        .build());
    }
}

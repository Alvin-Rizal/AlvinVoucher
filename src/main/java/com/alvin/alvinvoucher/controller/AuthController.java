package com.alvin.alvinvoucher.controller;

import com.alvin.alvinvoucher.dto.request.AuthRequest;
import com.alvin.alvinvoucher.dto.response.DefaultResponse;
import com.alvin.alvinvoucher.dto.response.LoginResponse;
import com.alvin.alvinvoucher.dto.response.RegisterResponse;
import com.alvin.alvinvoucher.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    @PostMapping("/register")
    public ResponseEntity registerCustomer(@RequestBody AuthRequest registerRequest) {
        try {
            RegisterResponse data = authService.register(registerRequest);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(DefaultResponse.builder()
                            .statusCode(HttpStatus.OK.value())
                            .message("Register successfully")
                            .data(data)
                            .build());
        }catch (ResponseStatusException e){
            return ResponseEntity.status(e.getStatusCode())
                    .body(DefaultResponse.builder()
                            .statusCode(e.getStatusCode().value())
                            .message(e.getReason())
                            .build());
        }
    }

    @PostMapping("/login")
    public ResponseEntity loginCustomer(@RequestBody AuthRequest loginRequest) {
        try {
            LoginResponse data = authService.login(loginRequest);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(DefaultResponse.builder()
                            .statusCode(HttpStatus.OK.value())
                            .message("Login successfully")
                            .data(data)
                            .build());
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode())
                    .body(DefaultResponse.builder()
                            .statusCode(e.getStatusCode().value())
                            .message(e.getReason())
                            .build());
        }
    }
}

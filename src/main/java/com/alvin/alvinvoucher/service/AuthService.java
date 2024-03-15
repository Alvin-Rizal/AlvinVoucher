package com.alvin.alvinvoucher.service;

import com.alvin.alvinvoucher.dto.request.AuthRequest;
import com.alvin.alvinvoucher.dto.response.LoginResponse;
import com.alvin.alvinvoucher.dto.response.RegisterResponse;

public interface AuthService {
    RegisterResponse register(AuthRequest registerRequest);
    LoginResponse login(AuthRequest loginRequest);
}

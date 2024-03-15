package com.alvin.alvinvoucher.mapper;

import com.alvin.alvinvoucher.dto.request.AuthRequest;
import com.alvin.alvinvoucher.entity.Role;
import com.alvin.alvinvoucher.entity.UserCredential;

import java.time.LocalDateTime;

public class UserCredentialMapper {

    public static UserCredential mapToUserCredential(AuthRequest authAdminRequest, String passwordHashed, Role role) {
        return UserCredential.builder()
                .username(authAdminRequest.getUsername())
                .password(passwordHashed)
                .role(role)
                .build();
    }

    public static UserCredential mapToUserCredentialCustomer(AuthRequest registerRequest,String passwordHashed, Role role) {
        return UserCredential.builder()
                .password(passwordHashed)
                .role(role)
                .username(registerRequest.getUsername())
                .build();
    }
}

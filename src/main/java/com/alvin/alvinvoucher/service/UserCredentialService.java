package com.alvin.alvinvoucher.service;

import com.alvin.alvinvoucher.entity.AppUser;
import com.alvin.alvinvoucher.entity.UserCredential;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserCredentialService extends UserDetailsService {

    AppUser loadUserByUsername(String username);
    UserCredential updateUserCredential(UserCredential userCredential);
}

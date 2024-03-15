package com.alvin.alvinvoucher.service.impl;

import com.alvin.alvinvoucher.entity.AppUser;
import com.alvin.alvinvoucher.entity.UserCredential;
import com.alvin.alvinvoucher.repository.UserCredentialRepository;
import com.alvin.alvinvoucher.service.UserCredentialService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserCredentialServiceImpl implements UserCredentialService {

    private final UserCredentialRepository userCredentialRepository;
    @Override
    public AppUser loadUserByUsername(String username) {
        UserCredential userCredential = userCredentialRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("invalid username"));
        return AppUser.builder()
                .id(userCredential.getId())
                .username(userCredential.getUsername())
                .password(userCredential.getPassword())
                .role(userCredential.getRole().getName())
                .build();
    }

    @Override
    public UserCredential updateUserCredential(UserCredential userCredential) {
        String username = userCredential.getUsername();
        if(username != null && loadUserByUsername(username) != null) return userCredentialRepository.save(userCredential);
        return null;
    }
}

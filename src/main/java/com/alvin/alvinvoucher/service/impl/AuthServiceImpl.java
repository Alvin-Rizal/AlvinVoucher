package com.alvin.alvinvoucher.service.impl;

import com.alvin.alvinvoucher.constant.ERole;
import com.alvin.alvinvoucher.dto.request.AuthRequest;
import com.alvin.alvinvoucher.dto.response.LoginResponse;
import com.alvin.alvinvoucher.dto.response.RegisterResponse;
import com.alvin.alvinvoucher.entity.AppUser;
import com.alvin.alvinvoucher.entity.Customer;
import com.alvin.alvinvoucher.entity.Role;
import com.alvin.alvinvoucher.entity.UserCredential;
import com.alvin.alvinvoucher.repository.UserCredentialRepository;
import com.alvin.alvinvoucher.security.JwtUtil;
import com.alvin.alvinvoucher.service.AuthService;
import com.alvin.alvinvoucher.service.CustomerService;
import com.alvin.alvinvoucher.service.RoleService;
import com.alvin.alvinvoucher.service.UserCredentialService;
import com.alvin.alvinvoucher.util.ValidationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static com.alvin.alvinvoucher.mapper.CustomerMapper.*;
import static com.alvin.alvinvoucher.mapper.UserCredentialMapper.mapToUserCredentialCustomer;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserCredentialRepository userCredentialRepository;
    private final UserCredentialService userCredentialService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final RoleService roleService;
    private final CustomerService customerService;
    private final ValidationUtil validationUtil;

    private Role getRole(ERole roleName) {
        Role role = Role.builder()
                .name(roleName)
                .build();
        role = roleService.getOrSave(role);
        return role;
    }

    private Authentication getAuthentication(AuthRequest loginRequest) {
        validationUtil.validate(loginRequest);
        return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail().toLowerCase(), loginRequest.getPassword()));
    }
    @Override
    public RegisterResponse register(AuthRequest registerRequest) {
        try {
            validationUtil.validate(registerRequest);
            Role role = getRole(ERole.CUSTOMER);

            String passwordHashed = passwordEncoder.encode(registerRequest.getPassword());
            UserCredential userCredential = mapToUserCredentialCustomer(registerRequest, passwordHashed, role);
            userCredentialRepository.saveAndFlush(userCredential);
            Customer customer = mapToCustomer(userCredential, registerRequest);
            customerService.create(customer);
            return mapToRegisterCustomer(userCredential, registerRequest);
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "User Already Exist");
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "An internal server error occurred", e);
        }
    }

    @Override
    public LoginResponse login(AuthRequest loginRequest) {
        try {
            Authentication authentication = getAuthentication(loginRequest);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            AppUser appUser = (AppUser) authentication.getPrincipal();
            String token = jwtUtil.generateToken(appUser);
            Optional<Customer> customerResponse = customerService.getCustomerByUserCredentialId(appUser.getId());
            return mapToLoginResponse(customerResponse, appUser.getRole(), token);
        } catch (BadCredentialsException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Invalid email or password", e);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "An internal server error occurred", e);
        }
    }
}

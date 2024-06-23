package com.tour.prevel.auth.service.impl;

import com.tour.prevel.auth.domain.User;
import com.tour.prevel.auth.dto.CreateUserRequest;
import com.tour.prevel.auth.dto.UserResponse;
import com.tour.prevel.auth.repository.AuthRepository;
import com.tour.prevel.auth.service.AuthService;
import com.tour.prevel.auth.utils.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final PasswordEncoder passwordEncoder;
    private final AuthRepository authRepository;

    @Override
    public UserResponse createUser(CreateUserRequest userRequest) {

        if (isEmailExists(userRequest.email())) {
            throw new IllegalArgumentException("Email already exists");
        }

        if (validate(userRequest)) {
            throw new IllegalArgumentException("Email or password is empty");
        }

        if (!userRequest.password().equals(userRequest.passwordConfirm())) {
            throw new IllegalArgumentException("Password and password confirm are not equal");
        }

        return save(userRequest);
    }

    private boolean validate(CreateUserRequest userRequest) {
        if (StringUtils.isEmpty(userRequest.email())) {
            throw new IllegalArgumentException("Email is empty");
        }

        if (StringUtils.isEmpty(userRequest.password())) {
            throw new IllegalArgumentException("Password is empty");
        }

        if (Validator.isValidEmail(userRequest.email())) {
            throw new IllegalArgumentException("Email is invalid");
        }

        if (Validator.isValidPassword(userRequest.password())) {
            throw new IllegalArgumentException("Password is invalid");
        }

        return true;
    }

    private UserResponse save(CreateUserRequest userRequest) {
        User save = authRepository.save(User.builder()
                .email(userRequest.email())
                .password(passwordEncoder.encode(userRequest.password()))
                .build());
        return new UserResponse(save.getEmail());
    }

    private boolean isEmailExists(String email) {
        return authRepository.existsById(email);
    }
}

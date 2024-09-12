package com.tour.prevel.auth.service.impl;

import com.tour.prevel.auth.domain.User;
import com.tour.prevel.auth.dto.CreateUserRequest;
import com.tour.prevel.auth.dto.UserResponse;
import com.tour.prevel.auth.mapper.AuthMapper;
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
    private final AuthMapper authMapper;

    @Override
    public UserResponse createUser(CreateUserRequest userRequest) {

        if (isEmailExists(userRequest.email())) {
            throw new IllegalArgumentException("Email already exists");
        }

        if (!validate(userRequest)) {
            throw new IllegalArgumentException("Email or password is empty");
        }

        if (!userRequest.password().equals(userRequest.passwordConfirm())) {
            throw new IllegalArgumentException("Password and password confirm are not equal");
        }

        return save(userRequest);
    }

    @Override
    public UserResponse getUser(String email) {
        User user = authRepository.findById(email).orElseGet(() -> {
            throw new IllegalArgumentException("User not found");
        });
        return authMapper.toUserResponse(user);
    }

    private boolean validate(CreateUserRequest userRequest) {
        if (StringUtils.isEmpty(userRequest.email())) {
            throw new IllegalArgumentException("Email is empty");
        }

        if (StringUtils.isEmpty(userRequest.password())) {
            throw new IllegalArgumentException("Password is empty");
        }

        if (StringUtils.isEmpty(userRequest.email())) {
            throw new IllegalArgumentException("Nickname is empty");
        }

        if (!Validator.isValidEmail(userRequest.email())) {
            throw new IllegalArgumentException("Email is invalid");
        }

        if (!Validator.isValidPassword(userRequest.password())) {
            throw new IllegalArgumentException("Password is invalid");
        }

        return true;
    }

    private UserResponse save(CreateUserRequest userRequest) {
        User user = authRepository.save(User.builder()
                .email(userRequest.email())
                .nickname(userRequest.nickname())
                .password(passwordEncoder.encode(userRequest.password()))
                .build());
        return authMapper.toUserResponse(user);
    }

    @Override
    public boolean isEmailExists(String email) {
        return authRepository.existsById(email);
    }

    @Override
    public UserResponse updateNickname(String userId, String nickname) {
        User user = authRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        user.setNickname(nickname);
        return authMapper.toUserResponse(authRepository.save(user));
    }
}

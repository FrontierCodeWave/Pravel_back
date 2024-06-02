package com.tour.prevel.auth.service.impl;

import com.tour.prevel.auth.domain.User;
import com.tour.prevel.auth.dto.AuthResponse;
import com.tour.prevel.auth.dto.LoginFormRequest;
import com.tour.prevel.auth.repository.AuthRepository;
import com.tour.prevel.auth.service.AuthService;
import com.tour.prevel.auth.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthRepository authRepository;
    private final PasswordEncoder encoder;
    private final JwtUtil jwtUtil;

    @Override
    public AuthResponse login(LoginFormRequest request) {
        User user = authRepository.findById(request.email())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        if (!encoder.matches(request.password(), user.getPassword())) {
            throw new BadCredentialsException("Invalid password");
        }

        String token = jwtUtil.createAccessToken(user);

        return AuthResponse.builder()
                .user(user)
                .token(token)
                .build();
    }
}

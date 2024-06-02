package com.tour.prevel.auth.controller;

import com.tour.prevel.auth.dto.AuthResponse;
import com.tour.prevel.auth.dto.LoginFormRequest;
import com.tour.prevel.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final JwtEncoder encoder;
    private final AuthService authService;

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginFormRequest loginRequest) {
        return authService.login(loginRequest);
    }
}

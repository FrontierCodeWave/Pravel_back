package com.tour.prevel.auth.controller;

import com.tour.prevel.auth.dto.CreateUserRequest;
import com.tour.prevel.auth.dto.UserResponse;
import com.tour.prevel.auth.service.AuthService;
import com.tour.prevel.auth.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;
    private final JwtUtil jwtUtil;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{email}")
    public UserResponse getUser(@PathVariable String email, @RequestBody String token) {
        String userEmail = jwtUtil.getUserId(token);

        if (!userEmail.equals(email)) {
            throw new IllegalArgumentException("Invalid token");
        }

        return authService.getUser(userEmail);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public boolean verifyUser() {
        return true;
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("join")
    public UserResponse join(@RequestBody CreateUserRequest userRequest) {
        return authService.createUser(userRequest);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("checkid/{email}")
    public boolean checkId(@PathVariable String email) {
        return authService.isEmailExists(email);
    }
}

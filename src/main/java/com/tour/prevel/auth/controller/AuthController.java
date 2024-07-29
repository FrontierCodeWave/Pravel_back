package com.tour.prevel.auth.controller;

import com.tour.prevel.auth.dto.CreateUserRequest;
import com.tour.prevel.auth.dto.UserResponse;
import com.tour.prevel.auth.service.AuthService;
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

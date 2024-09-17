package com.tour.prevel.auth.controller;

import com.tour.prevel.auth.dto.CreateUserRequest;
import com.tour.prevel.auth.dto.UserResponse;
import com.tour.prevel.auth.service.AuthService;
import com.tour.prevel.auth.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;
    private final JwtUtil jwtUtil;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public UserResponse getUser(Authentication auth) {
        return authService.getUser(auth.getName());
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("verify")
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

    @ResponseStatus(HttpStatus.OK)
    @PutMapping
    public UserResponse updateUser(
            MultipartFile profile, String nickname,
            Authentication auth) {
        return authService.updateUser(auth.getName(), profile, nickname);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("check-password")
    public boolean checkPassword(@RequestBody String password, Authentication auth) {
        return authService.checkPassword(auth.getName(), password);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/password")
    public UserResponse updatePassword(@RequestBody String password, Authentication auth) {
        return authService.updatePassword(auth.getName(), password);
    }
}

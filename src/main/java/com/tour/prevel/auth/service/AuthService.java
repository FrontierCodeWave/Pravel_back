package com.tour.prevel.auth.service;

import com.tour.prevel.auth.dto.CreateUserRequest;
import com.tour.prevel.auth.dto.UserResponse;

public interface AuthService {

    UserResponse createUser(CreateUserRequest userRequest);
    boolean isEmailExists(String email);
}

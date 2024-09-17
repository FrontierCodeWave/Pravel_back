package com.tour.prevel.auth.service;

import com.tour.prevel.auth.dto.CreateUserRequest;
import com.tour.prevel.auth.dto.UserResponse;
import org.springframework.web.multipart.MultipartFile;

public interface AuthService {

    UserResponse createUser(CreateUserRequest userRequest);
    boolean isEmailExists(String email);
    UserResponse getUser(String email);
    UserResponse updateUser(String userId, MultipartFile profile, String nickname);
    boolean checkPassword(String userId, String password);
    UserResponse updatePassword(String userId, String password);
}

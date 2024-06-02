package com.tour.prevel.auth.service;

import com.tour.prevel.auth.dto.AuthResponse;
import com.tour.prevel.auth.dto.LoginFormRequest;

public interface AuthService {

    AuthResponse login(LoginFormRequest request);
}

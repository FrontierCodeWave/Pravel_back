package com.tour.prevel.global.auth.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tour.prevel.auth.domain.User;
import com.tour.prevel.auth.utils.JwtUtil;
import com.tour.prevel.global.auth.dto.LoginSuccessResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;

public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    final private JwtUtil jwtUtil;

    public LoginSuccessHandler(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication
    ) throws IOException {
        if (!(authentication.getPrincipal() instanceof User)) return;

        User sessionUser = (User) authentication.getPrincipal();
        String token = jwtUtil.createAccessToken(sessionUser);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().write(new ObjectMapper().writeValueAsString(LoginSuccessResponse
                .builder()
                .token(token)
                .user(sessionUser)
                .build()));
    }
}

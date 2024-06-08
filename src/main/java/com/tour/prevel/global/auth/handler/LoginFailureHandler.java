package com.tour.prevel.global.auth.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tour.prevel.global.auth.dto.LoginFailResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.MessageSource;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.util.Assert;

import java.io.IOException;
import java.time.LocalDateTime;

public class LoginFailureHandler implements AuthenticationFailureHandler {

    final private MessageSource messageSource;

    public LoginFailureHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @Override
    public void onAuthenticationFailure(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException exception
    ) throws IOException {
        LoginFailResponse failureResponse = createFailureResponse(exception);

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        response.getWriter().write(new ObjectMapper().writeValueAsString(failureResponse));
    }

    private LoginFailResponse createFailureResponse(AuthenticationException exception) {
        Assert.notNull(exception, "Exception must not be null");
        return LoginFailResponse.builder()
                .message(messageSource.getMessage("error.auth.invalid", null, null))
                .build();
    }
}

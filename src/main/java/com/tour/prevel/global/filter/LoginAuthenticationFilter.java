package com.tour.prevel.global.filter;

import com.tour.prevel.global.auth.LoginAuthenticationProvider;
import com.tour.prevel.global.auth.LoginAuthenticationToken;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationConverter;
import org.springframework.security.web.authentication.www.BasicAuthenticationConverter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;


@Slf4j
@Component
public class LoginAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    private static final AntPathRequestMatcher SSO_LOGIN_ANT_PATH_REQUEST_MATCHER
            = new AntPathRequestMatcher("/api/auth/login", HttpMethod.POST.name());

    private final LoginAuthenticationProvider authenticationProvider;
    private AuthenticationConverter authenticationConverter = new BasicAuthenticationConverter();

    public LoginAuthenticationFilter(AuthenticationManager authenticationManager, LoginAuthenticationProvider authenticationProvider) {
        super(SSO_LOGIN_ANT_PATH_REQUEST_MATCHER, authenticationManager);
        this.authenticationProvider = authenticationProvider;
    }

    @Override
    public Authentication attemptAuthentication(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws AuthenticationException {
        if (!request.getMethod().equals(HttpMethod.POST.name())) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        }

        Authentication converted = authenticationConverter.convert(request);

        if (converted == null) {
            throw new AuthenticationServiceException("Authentication request cannot be null");
        }

        LoginAuthenticationToken authentication = new LoginAuthenticationToken(converted.getPrincipal(), converted.getCredentials());
        return this.authenticationProvider.authenticate(authentication);
    }
}

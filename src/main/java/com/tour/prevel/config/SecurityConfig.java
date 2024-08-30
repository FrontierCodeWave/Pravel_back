package com.tour.prevel.config;

import com.tour.prevel.auth.utils.JwtUtil;
import com.tour.prevel.global.auth.LoginAuthenticationProvider;
import com.tour.prevel.global.auth.handler.LoginFailureHandler;
import com.tour.prevel.global.auth.handler.LoginSuccessHandler;
import com.tour.prevel.global.filter.LoginAuthenticationFilter;
import jakarta.servlet.Filter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.server.resource.web.BearerTokenAuthenticationEntryPoint;
import org.springframework.security.oauth2.server.resource.web.access.BearerTokenAccessDeniedHandler;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final MessageSource messageSource;
    private final LoginAuthenticationProvider authenticationProvider;
    private final JwtUtil jwtUtil;

    @Bean
    public AuthenticationManager authenticationManager() {
        return new ProviderManager(authenticationProvider);
    }

    public Filter loginAuthenticationFilter()  {
        LoginAuthenticationFilter authenticationFilter = new LoginAuthenticationFilter();
        authenticationFilter.setAuthenticationManager(authenticationManager());
        authenticationFilter.setAuthenticationSuccessHandler(loginSuccessHandler());
        authenticationFilter.setAuthenticationFailureHandler(loginFailureHandler());
        return authenticationFilter;
    }

    private AuthenticationSuccessHandler loginSuccessHandler() {
        return new LoginSuccessHandler(jwtUtil);
    }

    private AuthenticationFailureHandler loginFailureHandler() {
        return new LoginFailureHandler(messageSource);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/images/**", "/css/**", "/js/**", "/favicon.ico", "/errors/**").permitAll()
                        .requestMatchers("/api/auth/login").permitAll()
                        .anyRequest().authenticated()
                );

        http
                .httpBasic(Customizer.withDefaults())
                .addFilterBefore(loginAuthenticationFilter(), BasicAuthenticationFilter.class)
                .cors(cors -> cors.disable())
                .csrf(csrf -> csrf.disable())
                .oauth2ResourceServer(
                        oauth2 -> oauth2.jwt(Customizer.withDefaults())

                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .exceptionHandling(exceptions -> exceptions
                        .authenticationEntryPoint(new BearerTokenAuthenticationEntryPoint())
                        .accessDeniedHandler(new BearerTokenAccessDeniedHandler()));

        return http.build();
    }

}

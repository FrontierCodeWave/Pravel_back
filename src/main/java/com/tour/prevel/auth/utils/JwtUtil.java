package com.tour.prevel.auth.utils;

import com.tour.prevel.auth.domain.User;
import com.tour.prevel.config.JwtKeyProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtUtil {

    private final JwtEncoder encoder;
    private final JwtDecoder decoder;
    private final JwtKeyProperties jwtKeyProperties;

    public String createAccessToken(User user) {
        return createToken(user, jwtKeyProperties.getExpirationTime());
    }

    private String createToken(User user, long expTime) {
        Instant now = Instant.now();

        String scope = user.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(now)
                .expiresAt(now.plusSeconds(expTime))
                .subject(user.getEmail())
                .claim("scope", scope)
                .claim("userId", user.getEmail())
                .build();

        return this.encoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }

    public String getUserId(String token) {
        if (isBearerToken(token)) {
            token = extractBearerToken(token);
        }

        Jwt decode = this.decoder.decode(token);

        return this.decoder.decode(token).getClaimAsString("userId");
    }

    public boolean validateToken(String token) {
        try {
            this.decoder.decode(token);
            return true;
        } catch (Exception e) {
            log.error("Error while validating token", e);
            return false;
        }
    }

    public boolean isBearerToken(String header) {
        return header.startsWith("Bearer ");
    }

    public String extractBearerToken(String header) {
        return header.replace("Bearer ", "");
    }
}

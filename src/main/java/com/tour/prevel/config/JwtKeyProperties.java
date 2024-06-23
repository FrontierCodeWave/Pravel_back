package com.tour.prevel.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cglib.core.KeyFactory;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

@Getter
@ConfigurationProperties("auth.key")
@AllArgsConstructor
public class JwtKeyProperties {

    private long expirationTime;
}

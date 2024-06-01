package com.tour.prevel.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

@Getter
@ConfigurationProperties("auth.key")
@AllArgsConstructor
public class JwtKeyProperties {

    private RSAPublicKey publicKey;

    private RSAPrivateKey privateKey;

    private long expirationTime;
}

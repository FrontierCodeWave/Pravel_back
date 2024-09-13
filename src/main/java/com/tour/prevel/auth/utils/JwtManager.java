package com.tour.prevel.auth.utils;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

@Getter
@ConfigurationProperties(prefix = "auth.key")
@AllArgsConstructor
public class JwtManager {

    private RSAPublicKey publicKey;
    private RSAPrivateKey privateKey;
}

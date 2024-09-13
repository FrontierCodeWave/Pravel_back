package com.tour.prevel.auth.utils;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

@Getter
@Setter
@ConfigurationProperties(prefix = "auth.key")
public class JwtManager {

    private RSAPublicKey publicKey;
    private RSAPrivateKey privateKey;
}

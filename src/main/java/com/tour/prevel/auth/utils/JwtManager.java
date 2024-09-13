package com.tour.prevel.auth.utils;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.security.converter.RsaKeyConverters;

import java.io.ByteArrayInputStream;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

@Getter
@ConfigurationProperties(prefix = "auth.key")
public class JwtManager {

    private RSAPublicKey publicKey;
    private RSAPrivateKey privateKey;

    public JwtManager(String publicKey, String privateKey) {
        this.publicKey = RsaKeyConverters.x509().convert(new ByteArrayInputStream(publicKey.getBytes()));;
        this.privateKey = RsaKeyConverters.pkcs8().convert(new ByteArrayInputStream(privateKey.getBytes()));;
    }
}

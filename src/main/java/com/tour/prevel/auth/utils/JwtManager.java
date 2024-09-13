package com.tour.prevel.auth.utils;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.security.converter.RsaKeyConverters;

import java.io.ByteArrayInputStream;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Arrays;
import java.util.stream.Stream;

@Getter
@ConfigurationProperties(prefix = "auth.key")
public class JwtManager {

    private RSAPublicKey publicKey;
    private RSAPrivateKey privateKey;

    public static byte[] replaceBytes(byte[] input) {
        for (int i = 0; i < input.length; i++) {
            if (input[i] == 92) {
                input[i] = 10;
            }
        }
        return input;
    }

    public JwtManager(String publicKey, String privateKey) {
        this.publicKey = RsaKeyConverters.x509().convert(new ByteArrayInputStream(
                replaceBytes(publicKey.replace("\\", System.lineSeparator()).getBytes())));
        this.privateKey = RsaKeyConverters.pkcs8().convert(new ByteArrayInputStream(
                replaceBytes(privateKey.replace("\\", System.lineSeparator()).getBytes())));
    }
}

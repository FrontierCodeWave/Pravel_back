package com.tour.prevel.auth.utils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

@Component
public class JwtManager {

    @Value("${auth.key.public}")
    RSAPublicKey publicKey;

    @Value("${auth.key.private}")
    RSAPrivateKey privateKey;

    public RSAPublicKey getPublicKey() {
        return this.publicKey;
    }

    public RSAPrivateKey getPrivateKey() {
        return this.privateKey;
    }
}

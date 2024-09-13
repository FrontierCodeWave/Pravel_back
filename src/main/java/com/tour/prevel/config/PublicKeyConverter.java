package com.tour.prevel.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.converter.RsaKeyConverters;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.security.interfaces.RSAPublicKey;

@Slf4j
@Component
@ConfigurationPropertiesBinding
public class PublicKeyConverter implements Converter<String, RSAPublicKey> {

    @Override
    public RSAPublicKey convert(String from) {
        log.info("Converting public key", from);
        return RsaKeyConverters.x509().convert(new ByteArrayInputStream(from.getBytes()));
    }
}

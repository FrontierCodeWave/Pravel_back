package com.tour.prevel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class PrevelApplication {

    public static void main(String[] args) {
        SpringApplication.run(PrevelApplication.class, args);
    }

}

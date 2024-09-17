package com.tour.prevel.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;
import java.io.IOException;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        try {
            File absoluteFile = new ClassPathResource("static/profile").getFile().getAbsoluteFile();
            registry.addResourceHandler("/profile/**")
                    .addResourceLocations("classpath:/static/profile/");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}

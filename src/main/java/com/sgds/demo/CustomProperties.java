package com.sgds.demo;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data
@Configuration
@ConfigurationProperties(prefix = "com.sgds.demo")
public class CustomProperties {

    private String apiUrl;
    
}
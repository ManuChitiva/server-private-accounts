package com.wowlibre.config;

import lombok.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.*;

@Configuration
@Component
@Data
public class Configurations {
    @Value("${google.api-secret}")
    private String apiSecret;
    @Value("${google.api-key}")
    private String apiKey;
}

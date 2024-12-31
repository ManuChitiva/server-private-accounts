package com.wowlibre.dto;

import lombok.*;

@Data
public class VerifyCaptchaResponse {
    private Boolean success;
    private String hostname;
}

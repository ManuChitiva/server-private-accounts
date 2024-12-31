package com.wowlibre.service;

import com.wowlibre.dto.*;

public interface GoogleService {
    VerifyCaptchaResponse verifyRecaptcha(VerifyCaptchaRequest request);
}

package com.wowlibre.config;

import org.slf4j.*;
import org.springframework.http.*;
import org.springframework.http.client.*;

import java.io.*;
import java.nio.charset.*;


public class RestTemplateHeaderModifierInterceptor implements ClientHttpRequestInterceptor {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(RestTemplateHeaderModifierInterceptor.class);

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        LOGGER.info(
                "===========================Request begin======================================");
        LOGGER.info("URI         : {}", request.getURI());
        LOGGER.info("Method      : {}", request.getMethod());
        LOGGER.info("Headers     : {}", request.getHeaders());
        LOGGER.info("Request body: {}", new String(body, StandardCharsets.UTF_8));
        LOGGER.info(
                "==========================Request end=========================================");
        return execution.execute(request, body);
    }
}

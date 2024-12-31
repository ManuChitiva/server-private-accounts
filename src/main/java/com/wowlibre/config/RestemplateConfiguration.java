package com.wowlibre.config;

import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.web.client.*;
import org.springframework.context.annotation.*;
import org.springframework.http.client.*;
import org.springframework.util.*;
import org.springframework.web.client.*;

import java.time.*;
import java.util.*;

@Configuration
public class RestemplateConfiguration {

    @Value("${restemplate.config.connect-timeout:60}")
    private Integer connectTimeout;
    @Value("${restemplate.config.read-timeout:30}")
    private Integer readTimeout;


    @Bean
    public RestTemplate getRestTemplate() {
        RestTemplate restTemplate = createRestTemplate(connectTimeout, readTimeout);
        List<ClientHttpRequestInterceptor> interceptors = restTemplate.getInterceptors();
        if (CollectionUtils.isEmpty(interceptors)) {
            interceptors = new ArrayList<>();
        }
        interceptors.add(new RestTemplateHeaderModifierInterceptor());
        restTemplate.setInterceptors(interceptors);
        return restTemplate;
    }

    private RestTemplate createRestTemplate(final int connectTimeout, final int readTimeout) {
        RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder();
        return restTemplateBuilder.setConnectTimeout(Duration.ofSeconds(connectTimeout))
                .setReadTimeout(Duration.ofSeconds(readTimeout)).build();
    }

}

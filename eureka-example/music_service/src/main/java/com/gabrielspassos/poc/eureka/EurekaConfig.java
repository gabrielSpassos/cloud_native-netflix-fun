package com.gabrielspassos.poc.eureka;

import okhttp3.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.WebApplicationContext;

@Configuration
public class EurekaConfig {

    @Bean
    public EurekaRegistry eurekaRegistry(EurekaModelRegistry eurekaModelRegistry, WebApplicationContext webApplicationContext, OkHttpClient okHttpClient) {
        return new EurekaRegistry(eurekaModelRegistry, webApplicationContext, okHttpClient);
    }

}

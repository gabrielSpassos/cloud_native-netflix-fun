package com.gabrielspassos.poc.comunication;

import okhttp3.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MusicServiceConfig {

    @Bean
    public MusicServiceConnector musicServiceConnector(OkHttpClient okHttpClient){
        return new MusicServiceConnector(okHttpClient);
    }
}

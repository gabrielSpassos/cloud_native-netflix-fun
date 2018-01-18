package com.gabrielspassos.poc.connector;

import feign.Feign;
import feign.Logger;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import feign.okhttp.OkHttpClient;
import feign.slf4j.Slf4jLogger;
import org.springframework.stereotype.Service;

@Service
public class PersonServiceConnector {

    public PersonServiceConnectorInterface run(){

        return Feign.builder()
                .client(new OkHttpClient())
                .encoder(new GsonEncoder())
                .decoder(new GsonDecoder())
                .logger(new Slf4jLogger(PersonServiceConnectorInterface.class))
                .logLevel(Logger.Level.FULL)
                .target(PersonServiceConnectorInterface.class, "http://localhost:9000/person/");
    }
}

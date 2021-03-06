package com.gabrielspassos.poc.healthcheck;

import java.io.IOException;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.gabrielspassos.poc.eureka.EurekaModelRegistry;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTask {

    private static final Logger log = LoggerFactory.getLogger(ScheduledTask.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    private final static String URL_BASE_HEALTH_CHECK = "http://localhost:8080/eureka/v2/apps/music-service/";

    @Autowired
    EurekaModelRegistry eurekaModelRegistry;

    @Autowired
    OkHttpClient okHttpClient;

    public ScheduledTask() throws UnknownHostException {
    }

    @Scheduled(fixedRate = 15000)
    public void reportCurrentTime() throws IOException {

        RequestBody body = RequestBody.create(MediaType.parse("application/json"),"");

        Request request = new Request.Builder().url(URL_BASE_HEALTH_CHECK+eurekaModelRegistry.getHostName()).put(body).build() ;
        okHttpClient.newCall(request).execute();
        log.info("The time is now {}", dateFormat.format(new Date()));

    }
}
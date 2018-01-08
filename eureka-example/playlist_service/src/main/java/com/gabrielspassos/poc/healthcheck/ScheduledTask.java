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

    @Autowired
    EurekaModelRegistry eurekaModelRegistry;

    OkHttpClient client = new OkHttpClient();

    public ScheduledTask() throws UnknownHostException {
    }

    @Scheduled(fixedRate = 10000)
    public void reportCurrentTime() throws IOException {

        RequestBody body = RequestBody.create(MediaType.parse("application/json"),"");


        Request request = new Request.Builder().url("http://localhost:8080/eureka/v2/apps/playlist-service/"+eurekaModelRegistry.getHostName()).put(body).build() ;

        client.newCall(request).execute();
        log.info("The time is now {}", dateFormat.format(new Date()));

    }
}
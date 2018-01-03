package com.gabrielspassos.poc.eureka;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class EurekaDiscovery {

    OkHttpClient client = new OkHttpClient();

    public EurekaModelDiscover getUrlFromMusicService() throws IOException{
        Request request = new Request.Builder()
                .url("http://localhost:8080/eureka/v2/apps/music-service")
                .addHeader("Accept","application/json")
                .build();
        Response responses = null;

        try {
            responses = client.newCall(request).execute();
        }catch (IOException e){
            e.printStackTrace();
        }

        String jsonString = responses.body().string();

        Gson gson = new GsonBuilder().create();
        EurekaModelDiscover eurekaModelDiscover = new EurekaModelDiscover();

        return eurekaModelDiscover = gson.fromJson(jsonString,EurekaModelDiscover.class);
    }
}

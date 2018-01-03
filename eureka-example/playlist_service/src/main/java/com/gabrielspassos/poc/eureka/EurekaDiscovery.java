package com.gabrielspassos.poc.eureka;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.stereotype.Component;

import java.io.IOException;


@Component
public class EurekaDiscovery {

    OkHttpClient client = new OkHttpClient();

    public EurekaModelDiscover getUrlFromMusicService() throws IOException, JSONException {
        Request request = new Request.Builder()
                .url("http://localhost:8080/eureka/v2/apps/music-service/music")
                .addHeader("Accept","application/json")
                .build();
        Response responses = null;

        try {
            responses = client.newCall(request).execute();
        }catch (IOException e){
            e.printStackTrace();
        }

        String jsonString = responses.body().string();

        EurekaModelDiscover eurekaModelDiscover = new EurekaModelDiscover();

        JSONObject jsonResult = new JSONObject(jsonString);
        jsonString = jsonResult.getString("instance");

        JSONObject jsonResult2 = new JSONObject(jsonString);
        jsonString = jsonResult2.getString("port");

        JSONObject jsonResult3 = new JSONObject(jsonString);

        eurekaModelDiscover.setIpAddr(jsonResult2.getString("ipAddr"));
        eurekaModelDiscover.setPort(jsonResult3.getString("$"));
        return eurekaModelDiscover;
    }
}

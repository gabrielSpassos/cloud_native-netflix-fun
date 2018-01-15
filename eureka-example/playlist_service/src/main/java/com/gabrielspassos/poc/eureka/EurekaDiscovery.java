package com.gabrielspassos.poc.eureka;

import com.gabrielspassos.poc.eureka.exceptions.EurekaException;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class EurekaDiscovery {

    private final static String EUREKA_APP_ID = "http://localhost:8080/eureka/v2/apps/music-service";
    private final static String HEADER_KEY = "Accept";
    private final static String HEADER_VALUE = "application/json";

    private OkHttpClient okHttpClient;

    public EurekaDiscovery(OkHttpClient okHttpClient) {
        this.okHttpClient = okHttpClient;
    }

    public List<EurekaModelDiscover> getUrlFromMusicService() throws IOException, JSONException{
        Request request = new Request.Builder()
                .url(EUREKA_APP_ID)
                .addHeader(HEADER_KEY, HEADER_VALUE)
                .build();
        try {
            Response responses = okHttpClient.newCall(request).execute();
            return parse(responses.body().string());
        }catch (IOException e){
            throw new EurekaException();
        }
    }

    private List<EurekaModelDiscover> parse(String jsonString) throws JSONException {

        EurekaModelDiscover eurekaModelDiscover = new EurekaModelDiscover();

        JSONObject jsonResult1 = new JSONObject(jsonString);
        jsonString = jsonResult1.getString("application");

        JSONObject jsonResult2 = new JSONObject(jsonString);
        jsonString = jsonResult2.getString("instance");

        JSONArray jsonResult3 = new JSONArray(jsonString);
        jsonString = jsonResult3.getString(0);

        JSONObject jsonResult4 = new JSONObject(jsonString);
        jsonString = jsonResult4.getString("port");

        JSONObject jsonResult5 = new JSONObject(jsonString);

        eurekaModelDiscover.setIpAddr(jsonResult4.getString("ipAddr"));
        eurekaModelDiscover.setPort(jsonResult5.getString("$"));

        ArrayList<EurekaModelDiscover> eurekaModelDiscovers = new ArrayList<>();
        eurekaModelDiscovers.add(eurekaModelDiscover);
        return eurekaModelDiscovers;
    }
}

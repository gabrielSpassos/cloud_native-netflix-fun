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

    OkHttpClient client = new OkHttpClient();
    private final String eurekaAppId = "http://localhost:8080/eureka/v2/apps/music-service";
    private final String headerKey = "Accept";
    private final String headerValue = "application/json";


    public List<EurekaModelDiscover> getUrlFromMusicService() throws IOException, JSONException, EurekaException {
        Request request = new Request.Builder()
                .url(eurekaAppId)
                .addHeader(headerKey,headerValue)
                .build();
        try {
            Response responses = client.newCall(request).execute();
            return parse(responses.body().string());
        }catch (IOException e){
            e.printStackTrace();
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

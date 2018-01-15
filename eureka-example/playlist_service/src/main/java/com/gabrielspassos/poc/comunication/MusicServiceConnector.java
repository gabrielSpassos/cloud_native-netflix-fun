package com.gabrielspassos.poc.comunication;

import com.gabrielspassos.poc.exception.FailToAcessOtherApi;
import com.gabrielspassos.poc.model.MusicModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class MusicServiceConnector {

    private OkHttpClient okHttpClient;

    public MusicServiceConnector(OkHttpClient okHttpClient) {
        this.okHttpClient = okHttpClient;
    }

    public MusicModel run(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        try {
            Response responses = okHttpClient.newCall(request).execute();
            return convertJsonToObject(responses.body().string());
        }catch (IOException e){
            throw new FailToAcessOtherApi();
        }

    }

    private MusicModel convertJsonToObject(String jsonString){
        Gson gson = new GsonBuilder().create();
        return  gson.fromJson(jsonString,MusicModel.class);
    }

}

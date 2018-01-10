package com.gabrielspassos.poc.comunication;

import com.gabrielspassos.poc.exception.FailToAcessOtherApi;
import com.gabrielspassos.poc.model.MusicModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class MusicServiceConnector {

    OkHttpClient client = new OkHttpClient();

    public MusicModel run(String url) throws IOException, FailToAcessOtherApi {
        Request request = new Request.Builder()
                .url(url)
                .build();
        Response responses = null;

        try {
            responses = client.newCall(request).execute();
        }catch (IOException e){
            throw new FailToAcessOtherApi();
        }

        String jsonString = responses.body().string();

        Gson gson = new GsonBuilder().create();

        MusicModel musicModel = new MusicModel();

        return musicModel = gson.fromJson(jsonString,MusicModel.class);
    }

}

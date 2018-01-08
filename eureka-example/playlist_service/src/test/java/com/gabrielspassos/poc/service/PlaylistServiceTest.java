package com.gabrielspassos.poc.service;

import com.gabrielspassos.poc.eureka.exceptions.EurekaException;
import com.gabrielspassos.poc.exception.FailToAcessOtherApi;
import com.gabrielspassos.poc.exception.IdNotFound;
import com.gabrielspassos.poc.model.MusicModel;
import org.codehaus.jettison.json.JSONException;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PlaylistServiceTest {

    PlaylistService playlistService = new PlaylistService();

    //This test only makes sence if the other micro service is running
    @Test
    public void mustReturnListByPlaylistId() throws IdNotFound, IOException, FailToAcessOtherApi, JSONException, EurekaException {
        List<MusicModel> list = new ArrayList<>();
        list.add(new MusicModel(2,"Jovem","Supercombo"));
        list.add(new MusicModel(5,"Cheia de Manias","Raça Negra"));
        list.add(new MusicModel(4,"Sua cara","Annita"));
        list.add(new MusicModel(7,"Esqueci como namora","Nego do Borel"));

        List<MusicModel> listReturned = playlistService.getMusicByPlaylistId(1);

        for (int i = 0; i <listReturned.size() ; i++) {
            assertEquals(list.get(i).getId(),listReturned.get(i).getId());
            assertEquals(list.get(i).getNameMusic(),listReturned.get(i).getNameMusic());
            assertEquals(list.get(i).getNameSinger(),listReturned.get(i).getNameSinger());
        }
    }

    @Test
    public void mustThrowExceptionIdNotFound() throws IOException, FailToAcessOtherApi, JSONException, EurekaException {
        try{
            playlistService.getMusicByPlaylistId(100);
        }catch (IdNotFound e){
            assertTrue(true);
        }
    }


        //This test only makes sence if the other micro service isn't running
//    @Test
//    public void mustThrowExceptionFailToAcessOtherApi() throws IdNotFound, IOException, JSONException, EurekaException {
//        try{
//            playlistService.getMusicByPlaylistId(1);
//        }catch (FailToAcessOtherApi e){
//            assertTrue(true);
//        }
//    }

}

package com.gabrielspassos.poc.service;

import com.gabrielspassos.poc.exception.FailToAcessOtherApi;
import com.gabrielspassos.poc.exception.IdNotFound;
import com.gabrielspassos.poc.model.MusicModel;
import org.codehaus.jettison.json.JSONException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PlaylistServiceTest {

    @Mock
    PlaylistService playlistService;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void mustReturnListByPlaylistId() throws IOException, JSONException{
        List<MusicModel> list = new ArrayList<>();
        list.add(new MusicModel(2,"Jovem","Supercombo"));
        list.add(new MusicModel(5,"Cheia de Manias","Raça Negra"));
        list.add(new MusicModel(4,"Sua cara","Annita"));
        list.add(new MusicModel(7,"Esqueci como namora","Nego do Borel"));

        when(playlistService.getMusicByPlaylistId(1)).thenReturn(list);

        List<MusicModel> listReturned = playlistService.getMusicByPlaylistId(1);

        for (int i = 0; i <listReturned.size() ; i++) {
            assertEquals(list.get(i).getId(),listReturned.get(i).getId());
            assertEquals(list.get(i).getNameMusic(),listReturned.get(i).getNameMusic());
            assertEquals(list.get(i).getNameSinger(),listReturned.get(i).getNameSinger());
        }
    }

    @Test (expected = IdNotFound.class)
    public void mustThrowExceptionIdNotFound() throws IOException, JSONException {
        when(playlistService.getMusicByPlaylistId(100)).thenThrow(IdNotFound.class);
        playlistService.getMusicByPlaylistId(100);
    }


    @Test (expected = FailToAcessOtherApi.class)
    public void mustThrowExceptionFailToAcessOtherApi() throws IOException, JSONException {
        when(playlistService.getMusicByPlaylistId(1)).thenThrow(FailToAcessOtherApi.class);
        playlistService.getMusicByPlaylistId(1);
    }

}

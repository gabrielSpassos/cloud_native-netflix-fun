package com.gabrielspassos.poc.service;

import com.gabrielspassos.poc.comunication.MusicServiceConnector;
import com.gabrielspassos.poc.eureka.EurekaDiscovery;
import com.gabrielspassos.poc.eureka.EurekaModelDiscover;
import com.gabrielspassos.poc.eureka.exceptions.EurekaException;
import com.gabrielspassos.poc.exception.FailToAcessOtherApi;
import com.gabrielspassos.poc.exception.IdNotFound;
import com.gabrielspassos.poc.model.MusicModel;
import com.gabrielspassos.poc.dao.PlaylistDAO;
import org.codehaus.jettison.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class PlaylistService {

    private PlaylistDAO playlistDAO = new PlaylistDAO(1);
    private List<Integer> ids = new ArrayList<>();
    private MusicServiceConnector musicServiceConnector = new MusicServiceConnector();
    private List<MusicModel> musics = new ArrayList<>();
    private EurekaDiscovery eurekaDiscovery = new EurekaDiscovery();

    @Autowired
    public PlaylistService() {
        playlistDAO.populate();
    }


    public List<MusicModel> getMusicByPlaylistId(int id) throws IdNotFound, IOException, FailToAcessOtherApi, JSONException, EurekaException {
        musics.clear();

        ids = getPlaylistById(id);

        List<EurekaModelDiscover> eurekaModelDiscoverList = eurekaDiscovery.getUrlFromMusicService();
        String url = eurekaModelDiscoverList.get(0).getIpAddr()+":"+eurekaModelDiscoverList.get(0).getPort();

        for (int i = 0; i <ids.size() ; i++) {
            musics.add(musicServiceConnector.run("http://"+url+ "/musics/" + ids.get(i)));
        }
        return musics;
    }

    private List<Integer> getPlaylistById(int id) throws IdNotFound {
        for (int i = 0; i < playlistDAO.getPlaylist().size() ; i++) {
            if(playlistDAO.getId() == id){
                return playlistDAO.getPlaylist();
            }
        }

        throw new IdNotFound();
    }
}

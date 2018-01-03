package com.gabrielspassos.poc.service;

import com.gabrielspassos.poc.comunication.Connector;
import com.gabrielspassos.poc.eureka.EurekaDiscovery;
import com.gabrielspassos.poc.eureka.EurekaModelDiscover;
import com.gabrielspassos.poc.exception.FailToAcessOtherApi;
import com.gabrielspassos.poc.exception.IdNotFound;
import com.gabrielspassos.poc.model.MusicModel;
import com.gabrielspassos.poc.dao.PlaylistDAO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PlaylistService {

    private PlaylistDAO playlistDAO = new PlaylistDAO(1);
    private List<Integer> ids = new ArrayList<>();
    private Connector connector = new Connector();
    private List<MusicModel> musics = new ArrayList<>();
    private EurekaDiscovery eurekaDiscovery = new EurekaDiscovery();

    public PlaylistService() {
        playlistDAO.populate();
    }


    public List<MusicModel> getMusicByPlaylistId(int id) throws IdNotFound, IOException, FailToAcessOtherApi {
        ids = getPlaylistById(id);
        EurekaModelDiscover eurekaModelDiscover = eurekaDiscovery.getUrlFromMusicService();
        for (int i = 0; i <ids.size() ; i++) {
            musics.add(connector.run(eurekaModelDiscover.getIpAddr() + ":"+ eurekaModelDiscover.getPort() + "/musics/" +ids.get(i)));
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

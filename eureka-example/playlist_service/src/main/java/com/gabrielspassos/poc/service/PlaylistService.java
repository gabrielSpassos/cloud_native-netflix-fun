package com.gabrielspassos.poc.service;

import com.gabrielspassos.poc.comunication.MusicServiceConnector;
import com.gabrielspassos.poc.eureka.EurekaDiscovery;
import com.gabrielspassos.poc.eureka.EurekaModelDiscover;
import com.gabrielspassos.poc.exception.FailToAcessOtherApi;
import com.gabrielspassos.poc.exception.IdNotFound;
import com.gabrielspassos.poc.model.MusicModel;
import com.gabrielspassos.poc.dao.PlaylistDAO;
import org.codehaus.jettison.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class PlaylistService {

    @Autowired
    PlaylistDAO playlistDAO;

    @Autowired
    EurekaDiscovery eurekaDiscovery;

    @Autowired
    MusicServiceConnector musicServiceConnector;

    public List<MusicModel> getMusicByPlaylistId(int id) throws IOException, JSONException{
        List<Integer> ids = getPlaylistById(id);

        String url = discoverUrlMusicService();

        return ids.stream().map(getIntegerMusicModelFunction(url)).collect(Collectors.toList());
    }

    private List<Integer> getPlaylistById(int id){
        if(playlistDAO.getID() == id){
            return playlistDAO.getPlaylist();
        }

        throw new IdNotFound();
    }

    private String discoverUrlMusicService() throws IOException, JSONException {
        List<EurekaModelDiscover> eurekaModelDiscoverList = eurekaDiscovery.getUrlFromMusicService();
        return eurekaModelDiscoverList.get(0).getIpAddr()+":"+eurekaModelDiscoverList.get(0).getPort();
    }

    private Function<Integer, MusicModel> getIntegerMusicModelFunction(String url) {
        return idMusic -> {
            try {
                return musicServiceConnector.run("http://"+url+ "/musics/" + idMusic);
            } catch (IOException e) {
                throw new FailToAcessOtherApi();
            }

        };
    }

}




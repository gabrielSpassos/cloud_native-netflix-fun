package com.gabrielspassos.poc;

import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PlaylistService {

    private PlaylistModel playlistModel = new PlaylistModel(1);
    private List<Integer> ids = new ArrayList<>();
    private Connector connector = new Connector();
    private List<MusicModel> musics = new ArrayList<>();

    public PlaylistService() {
        playlistModel.populate();
    }


    public List<MusicModel> getMusicByPlaylistId(int id) throws IdNotFound, IOException, ParseException {
        ids = getPlaylistById(id);
        for (int i = 0; i <ids.size() ; i++) {
            musics.add(connector.run("http://localhost:9000/musics/"+ids.get(i)));
        }
        return musics;
    }

    private List<Integer> getPlaylistById(int id) throws IdNotFound {
        for (int i = 0; i <playlistModel.getPlaylist().size() ; i++) {
            if(playlistModel.getId() == id){
                return playlistModel.getPlaylist();
            }
        }

        throw new IdNotFound("Id not found");
    }
}

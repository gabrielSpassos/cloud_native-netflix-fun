package com.gabrielspassos.poc.dao;

import java.util.ArrayList;
import java.util.List;

public class PlaylistDAO {

    private int id;
    private List<Integer> playlist = new ArrayList<>();

    public PlaylistDAO(int id) {
        this.id = id;
    }

    public List<Integer> getPlaylist() {
        return playlist;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void populate(){
        playlist.add(2);
        playlist.add(5);
        playlist.add(4);
        playlist.add(7);
    }
}

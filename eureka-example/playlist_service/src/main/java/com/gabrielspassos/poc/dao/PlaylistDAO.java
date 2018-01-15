package com.gabrielspassos.poc.dao;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PlaylistDAO {

    private final static int ID = 1;
    private final List<Integer> playlist = new ArrayList<>();

    public PlaylistDAO() {
        playlist.add(2);
        playlist.add(5);
        playlist.add(4);
        playlist.add(7);
    }

    public List<Integer> getPlaylist() {
        return playlist;
    }

    public static int getID() {
        return ID;
    }
}

package com.gabrielspassos.poc.model;

public class MusicModel {

    private int id;
    private String nameMusic;
    private String nameSinger;

    public MusicModel() {
    }

    public MusicModel(int id, String nameMusic, String nameSinger) {
        this.id = id;
        this.nameMusic = nameMusic;
        this.nameSinger = nameSinger;
    }

    public String getNameMusic() {
        return nameMusic;
    }

    public void setNameMusic(String nameMusic) {
        this.nameMusic = nameMusic;
    }

    public String getNameSinger() {
        return nameSinger;
    }

    public void setNameSinger(String nameSinger) {
        this.nameSinger = nameSinger;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}



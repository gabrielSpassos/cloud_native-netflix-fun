package com.gabrielspassos.poc.dao;

import com.gabrielspassos.poc.model.MusicModel;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class MusicDAO {

    private List<MusicModel> musicModelList = new ArrayList<>();

    public MusicDAO() {
        musicModelList.add(new MusicModel(1,"KO","Pablo Vittar"));
        musicModelList.add(new MusicModel(2,"Jovem","Supercombo"));
        musicModelList.add(new MusicModel(3,"Always","Blink-182"));
        musicModelList.add(new MusicModel(4,"Sua cara","Annita"));
        musicModelList.add(new MusicModel(5,"Cheia de Manias","Raça Negra"));
        musicModelList.add(new MusicModel(6,"Deixe-me ir","1Kilo"));
        musicModelList.add(new MusicModel(7,"Esqueci como namora","Nego do Borel"));
        musicModelList.add(new MusicModel(8,"Californication","Red Hot Chili Pappers"));
    }

    public List<MusicModel> getMusicModelList() {
        return musicModelList;
    }

}

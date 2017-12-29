package com.gabrielspassos.poc.service;

import com.gabrielspassos.poc.dao.MusicList;
import com.gabrielspassos.poc.exceptions.IdNotExistException;
import com.gabrielspassos.poc.model.MusicModel;

public class MusicService {

    MusicList musicList = new MusicList();
    MusicModel musicModel = new MusicModel();

    public MusicService() {
        musicList.populateList();
    }

    public MusicModel getMusicById(int id) throws IdNotExistException {
        for (int i = 0; i < musicList.getMusicModelList().size() ; i++) {
            if(musicList.getMusicModelList().get(i).getId() == id){
                musicModel.setId(id);
                musicModel.setNameMusic(musicList.getMusicModelList().get(i).getNameMusic());
                musicModel.setNameSinger(musicList.getMusicModelList().get(i).getNameSinger());
                return musicModel;
            }
        }

        throw new IdNotExistException();
    }
}

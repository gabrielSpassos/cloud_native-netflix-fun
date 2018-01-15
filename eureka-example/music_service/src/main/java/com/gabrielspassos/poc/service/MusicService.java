package com.gabrielspassos.poc.service;

import com.gabrielspassos.poc.dao.MusicDAO;
import com.gabrielspassos.poc.exceptions.IdNotExistException;
import com.gabrielspassos.poc.model.MusicModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MusicService {

    @Autowired
    MusicDAO musicDAO;

    public MusicModel getMusicById(int id){
        return musicDAO.getMusicModelList().stream()
                    .filter(music -> music.getId() == id)
                    .findFirst()
                    .orElseThrow(IdNotExistException::new);
    }

}

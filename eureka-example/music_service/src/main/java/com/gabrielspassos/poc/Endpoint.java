package com.gabrielspassos.poc;

import com.gabrielspassos.poc.exceptions.IdNotExistException;
import com.gabrielspassos.poc.model.MusicModel;
import com.gabrielspassos.poc.service.MusicService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Endpoint {

    MusicService musicService = new MusicService();

    @RequestMapping(value = "/musics/{id}",
                    method = RequestMethod.GET)
    public MusicModel getMusicById(@PathVariable("id") int id) throws IdNotExistException {
        return musicService.getMusicById(id);
    }

}

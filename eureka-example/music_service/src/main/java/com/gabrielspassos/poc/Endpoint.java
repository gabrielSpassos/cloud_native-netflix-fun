package com.gabrielspassos.poc;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Endpoint {

    MusicService musicService = new MusicService();

    @RequestMapping(value = "/musics/{id}",
                    method = RequestMethod.GET)
    public MusicModel getMusicById(@PathVariable("id") int id){
        return musicService.getMusicById(id);
    }

    @RequestMapping(value = "/musics",
            method = RequestMethod.GET)
    public String test(){
        return "test";
    }
}

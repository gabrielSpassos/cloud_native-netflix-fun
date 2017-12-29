package com.gabrielspassos.poc;

import org.json.simple.parser.ParseException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class Endpoint {

    PlaylistService playlistService = new PlaylistService();


    @RequestMapping(value = "/playlist/{id}",
            method = RequestMethod.GET)
    public List<MusicModel> getMusicById(@PathVariable("id") int id) throws ParseException, IdNotFound, IOException {
        return playlistService.getMusicByPlaylistId(id);
    }



}

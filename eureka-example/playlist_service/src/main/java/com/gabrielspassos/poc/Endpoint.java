package com.gabrielspassos.poc;

import com.gabrielspassos.poc.exception.FailToAcessOtherApi;
import com.gabrielspassos.poc.exception.IdNotFound;
import com.gabrielspassos.poc.model.MusicModel;
import com.gabrielspassos.poc.service.PlaylistService;
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
    public List<MusicModel> getMusicById(@PathVariable("id") int id) throws IdNotFound, IOException, FailToAcessOtherApi {
        return playlistService.getMusicByPlaylistId(id);
    }



}

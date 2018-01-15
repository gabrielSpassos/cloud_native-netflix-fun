package com.gabrielspassos.poc;

import com.gabrielspassos.poc.exception.IdNotFound;
import com.gabrielspassos.poc.model.MusicModel;
import com.gabrielspassos.poc.service.PlaylistService;
import org.codehaus.jettison.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class Endpoint {

    @Autowired
    PlaylistService playlistService;

    @RequestMapping(value = "/playlist/{id}",
            method = RequestMethod.GET)
    public List<MusicModel> getMusicById(@PathVariable("id") int id) throws IOException, JSONException {
        return playlistService.getMusicByPlaylistId(id);
    }

}

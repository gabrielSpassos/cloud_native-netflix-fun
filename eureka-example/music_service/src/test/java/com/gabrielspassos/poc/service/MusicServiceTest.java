package com.gabrielspassos.poc.service;

import com.gabrielspassos.poc.exceptions.IdNotExistException;
import com.gabrielspassos.poc.model.MusicModel;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MusicServiceTest {

    MusicService musicService = new MusicService();

    @Test
    public void mustReturnMusicById(){
        MusicModel musicReturned = musicService.getMusicById(1);
        assertEquals(1,musicReturned.getId());
        assertEquals("KO",musicReturned.getNameMusic());
        assertEquals("Pablo Vittar",musicReturned.getNameSinger());
    }

    @Test(expected = IdNotExistException.class)
    public void mustThrowException(){
        musicService.getMusicById(100);
    }

}

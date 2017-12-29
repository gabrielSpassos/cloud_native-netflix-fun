package com.gabrielspassos.poc.service;

import com.gabrielspassos.poc.exceptions.IdNotExistException;
import com.gabrielspassos.poc.model.MusicModel;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

public class MusicServiceTest {

    MusicService musicService = new MusicService();

    @Test
    public void mustReturnMusicById() throws IdNotExistException {
        MusicModel musicReturned = musicService.getMusicById(1);
        assertEquals(1,musicReturned.getId());
        assertEquals("KO",musicReturned.getNameMusic());
        assertEquals("Pablo Vittar",musicReturned.getNameSinger());
    }

    @Test
    public void mustThrowException(){
        try{
            musicService.getMusicById(100);
        }catch (IdNotExistException e){
            assertTrue(true);
        }
    }

}

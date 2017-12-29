package com.gabrielspassos.poc;

public class MusicService {

    MusicList musicList = new MusicList();
    MusicModel musicModel = new MusicModel();

    public MusicService() {
        musicList.populateList();
    }

    public MusicModel getMusicById(int id){
        for (int i = 0; i < musicList.getMusicModelList().size() ; i++) {
            if(musicList.getMusicModelList().get(i).getId() == id){
                musicModel.setId(id);
                musicModel.setNameMusic(musicList.getMusicModelList().get(i).getNameMusic());
                musicModel.setNameSinger(musicList.getMusicModelList().get(i).getNameSinger());
                return musicModel;
            }
        }

        return new MusicModel(100,"musica invalida","artista invalido");
    }
}

package com.MelodyMateBackend.service;

import com.MelodyMateBackend.domain.Song;
import com.MelodyMateBackend.mapper.SongMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SongServiceImpl implements SongService {

    private SongMapper dao;

    @Autowired
    public SongServiceImpl(SongMapper dao) {
        this.dao = dao;
    }


    @Override
    public List<Song> getAllSong() {
        return dao.getAllSong();
    }

    @Override
    public void chartSave(Song song) {
        dao.chartSave(song);
    }


}

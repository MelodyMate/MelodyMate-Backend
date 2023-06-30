package com.MelodyMateBackend.service;


import com.MelodyMateBackend.domain.Song;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface SongService {

    List<Song> getAllSong();

    void chartSave(Song song);

}

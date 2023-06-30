package com.MelodyMateBackend.mapper;

import com.MelodyMateBackend.domain.Song;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SongMapper {
    List<Song> getAllSong();

    void chartSave(Song song);
}

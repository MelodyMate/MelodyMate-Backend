package com.MelodyMateBackend.service;


import com.MelodyMateBackend.domain.Song;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface SongService {

    // 노래목록 가져오기
    List<Song> getAllSong();

    // 크롤링 데이터 저장
    void chartSave(Song song);

}

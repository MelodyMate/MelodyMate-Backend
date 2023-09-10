package com.melodymatebackend.music.application;

import com.melodymatebackend.music.domain.Music;
import com.melodymatebackend.music.domain.MusicRepository;
import com.melodymatebackend.music.exception.NoDataFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
@Service
public class MusicService {

    private final MusicRepository musicRepository;

    public List<Music> musicList(LocalDate rankDate) {
        return musicRepository.findAllByRankDate(rankDate);
    }

    public List<Music> musicLatestData(LocalDate date){
        List<Music> musicList = musicRepository.findAllByRankDate(date);

        if(musicList.isEmpty()){
            musicList = musicRepository.findLatestDataByRanking();
        }

        return musicList;
    }

}
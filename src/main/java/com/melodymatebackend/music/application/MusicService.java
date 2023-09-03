package com.melodymatebackend.music.application;

import com.melodymatebackend.music.domain.Music;
import com.melodymatebackend.music.domain.MusicRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@Transactional
@Slf4j
@Service
public class MusicService {

    private final MusicRepository musicRepository;

    public List<Music> musicList(LocalDate rankDate) {
        return musicRepository.findAllByRankDate(rankDate);
    }
}
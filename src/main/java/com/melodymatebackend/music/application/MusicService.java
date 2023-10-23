package com.melodymatebackend.music.application;

import com.melodymatebackend.music.domain.Music;
import com.melodymatebackend.music.domain.MusicRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
@Service
public class MusicService {

    private final MusicRepository musicRepository;

    public List<Music> musicList(LocalDate rankDate) {
        return musicRepository.findAll();
    }

    public boolean existsMusic(String artist, String title){
        return musicRepository.existsByArtistAndTitle(artist, title);
    }

    public Music findByArtistAndTitle(String artist, String title){
        return musicRepository.findByArtistAndTitle(artist, title);
    }


    
}
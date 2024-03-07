package com.melodymatebackend.music.application;

import com.melodymatebackend.music.domain.Music;
import com.melodymatebackend.music.domain.MusicRepository;
import com.melodymatebackend.music.domain.Ranking;
import com.melodymatebackend.music.domain.RankingsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.*;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
@Service
public class MusicService {

    private final MusicRepository musicRepository;
    private final RankingsRepository rankingsRepository;

    public List<Music> musicList(LocalDate rankDate) {
        return musicRepository.findAll();
    }

    public boolean existsMusic(String artist, String title) {
        return musicRepository.existsByArtistAndTitle(artist, title);
    }

    public Music findByArtistAndTitle(String artist, String title) {
        return musicRepository.findByArtistAndTitle(artist, title);
    }

    public Map<String, Object> getMusicList(LocalDate rankDate) {
        List<Ranking> rankings = rankingsRepository.findByRankDateOrderByIdAsc(rankDate);
        Map<String, Object> result = new LinkedHashMap<>();
        result.put("count", rankings.size());
        result.put("data", rankings);

        return result;
    }

    public void deleteRankingByRankDate(LocalDate rankDate) {
        rankingsRepository.deleteRankingByRankDate(rankDate);
    }


}
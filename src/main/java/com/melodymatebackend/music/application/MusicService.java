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

    public List<Map<String, Object>> getMusicList(LocalDate rankDate) {
        List<Ranking> rankings = rankingsRepository.findByRankDate(rankDate);
        List<Map<String, Object>> result = new ArrayList<>();

        for (Ranking ranking : rankings) {
            Map<String, Object> rankingData = new HashMap<>();
            rankingData.put("ranking", ranking.getRank());
            rankingData.put("musicTitle", ranking.getMusic().getTitle());
            rankingData.put("artist", ranking.getMusic().getArtist());
            rankingData.put("url", ranking.getMusic().getUrl());
            rankingData.put("thumbnail", ranking.getMusic().getThumbnail());
            rankingData.put("duration", ranking.getMusic().getDuration());
            rankingData.put("viewCount", ranking.getMusic().getViewCount());
            rankingData.put("releaseDate", ranking.getMusic().getReleaseDate());
            rankingData.put("rankDate", ranking.getMusic().getReleaseDate());

            result.add(rankingData);
        }

        return result;
    }

    public void deleteRankingByRankDate(LocalDate rankDate){
        rankingsRepository.deleteRankingByRankDate(rankDate);
    }

}
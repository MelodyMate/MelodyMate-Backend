package com.melodymatebackend.music.application;

import com.melodymatebackend.music.application.dto.NewMusicResponse;
import com.melodymatebackend.music.application.dto.NewRankingResponse;
import com.melodymatebackend.music.domain.Music;
import com.melodymatebackend.music.domain.MusicRepository;
import com.melodymatebackend.music.domain.Ranking;
import com.melodymatebackend.music.domain.RankingsRepository;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public NewRankingResponse newGetMusicList(LocalDate rankDate) {

        if (rankDate == null) {
            rankDate = LocalDate.now();
        }

        List<Ranking> rankings = rankingsRepository.findByRankDateOrderByRankAsc(rankDate);

        List<NewMusicResponse> list = rankings.stream()
            .sorted(Comparator.comparing(Ranking::getRank))
            .map(NewMusicResponse::new)
            .toList();

        int size = list.size();
        LocalDate now = LocalDate.now();

        return new NewRankingResponse(size, now, list);
    }

    public List<Map<String, Object>> getMusicList(LocalDate rankDate) {
        if (rankDate == null) {
            rankDate = LocalDate.now();
        }

        List<Ranking> rankings = rankingsRepository.findByRankDateOrderByIdAsc(rankDate);

        List<Map<String, Object>> result = new ArrayList<>();

        for (Ranking ranking : rankings) {
            Map<String, Object> rankingData = new HashMap<>();
            rankingData.put("ranking", ranking.getRank());
            rankingData.put("musicTitle", ranking.getMusic().getTitle());
            rankingData.put("artist", ranking.getMusic().getArtist());
            rankingData.put("url", ranking.getMusic().getUrl());
            rankingData.put("thumbnail", ranking.getMusic().getThumbnail());
            rankingData.put("duration", ranking.getMusic().getDuration());
            rankingData.put("viewCount", ranking.getMusic().getViewCountList());
            rankingData.put("releaseDate", ranking.getMusic().getReleaseDate());
            rankingData.put("rankDate", ranking.getRankDate());
            result.add(rankingData);
        }

        return result;
    }

    public void deleteRankingByRankDate(LocalDate rankDate) {
        rankingsRepository.deleteRankingByRankDate(rankDate);
    }


}
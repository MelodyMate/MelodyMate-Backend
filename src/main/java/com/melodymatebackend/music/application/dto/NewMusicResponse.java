package com.melodymatebackend.music.application.dto;

import com.melodymatebackend.music.domain.Ranking;
import com.melodymatebackend.music.domain.ViewCount;
import java.util.Comparator;

public record NewMusicResponse(
    int ranking,
    String musicTitle,
    String artist,
    String url,
    String thumbnail,
    String duration,
    String viewCount,
    String releaseDate
) {

    public NewMusicResponse(Ranking ranking) {
        this(
            ranking.getRank(),
            ranking.getMusic().getTitle(),
            ranking.getMusic().getArtist(),
            ranking.getMusic().getUrl(),
            ranking.getMusic().getThumbnail(),
            ranking.getMusic().getDuration(),
            ranking.getMusic().getViewCountList()
                .stream()
                .sorted(Comparator.comparing(ViewCount::getViewCount))
                .map(ViewCount::getViewCount)
                .findFirst()
                .orElse(null),
            ranking.getMusic().getReleaseDate()
        );
    }
}
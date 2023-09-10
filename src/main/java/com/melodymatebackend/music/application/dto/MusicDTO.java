package com.melodymatebackend.music.application.dto;

import com.melodymatebackend.music.domain.Music;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class MusicDTO {
    private int ranking;

    private String url;

    private String musicTitle;

    private String artist;

    private String thumbnail;

    private String duration;

    private String viewCount;

    private String releaseDate;

    private LocalDate rankDate;

    public Music toEntity() {
        return Music.builder()
                .ranking(ranking)
                .url(url)
                .musicTitle(musicTitle)
                .artist(artist)
                .thumbnail(thumbnail)
                .duration(duration)
                .viewCount(viewCount)
                .releaseDate(releaseDate)
                .rankDate(rankDate)
                .build();
    }
}

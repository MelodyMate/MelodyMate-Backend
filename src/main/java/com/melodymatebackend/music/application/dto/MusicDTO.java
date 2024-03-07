package com.melodymatebackend.music.application.dto;

import com.melodymatebackend.music.domain.Music;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MusicDTO {

    private String url;
    private String title;
    private String artist;
    private String thumbnail;
    private String duration;
    private String viewCount;
    private String releaseDate;

    public Music toEntity() {
        return Music.builder()
                .url(url)
                .title(title)
                .artist(artist)
                .thumbnail(thumbnail)
                .duration(duration)
                .viewCount(viewCount)
                .releaseDate(releaseDate)
                .build();
    }

    public Music updateViewCount(){
        return Music.builder()
                .viewCount(viewCount)
                .build();
    }

    public boolean isValid() {
        return !url.isEmpty() && !artist.isEmpty() && !title.isEmpty() && !thumbnail.isEmpty()
                && !duration.isEmpty() && !viewCount.isEmpty() && !releaseDate.isEmpty();
    }

}

package com.melodymatebackend.music.application.dto;

import com.melodymatebackend.music.domain.Music;
import com.melodymatebackend.music.domain.ViewCount;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class MusicDto {

    private String url;
    private String title;
    private String artist;
    private String thumbnail;
    private String duration;
    private String viewCount;
    private List<ViewCount> viewCountList;
    private String releaseDate;

    public Music toEntity() {
        return Music.builder()
                .url(url)
                .title(title)
                .artist(artist)
                .thumbnail(thumbnail)
                .duration(duration)
                .viewCountList(viewCountList)
                .releaseDate(releaseDate)
                .build();
    }

    public boolean isValid() {
        return !url.isEmpty() && !artist.isEmpty() && !title.isEmpty() && !thumbnail.isEmpty()
                && !duration.isEmpty() && !viewCount.isEmpty() && !releaseDate.isEmpty();
    }

}

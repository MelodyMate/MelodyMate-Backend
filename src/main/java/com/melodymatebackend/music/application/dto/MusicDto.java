package com.melodymatebackend.music.application.dto;

import com.melodymatebackend.music.domain.Music;
import com.melodymatebackend.music.domain.ViewCount;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MusicDto {

    private String url;
    private String title;
    private String artist;
    private String thumbnail;
    private String duration;
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
            && !duration.isEmpty() && !releaseDate.isEmpty();
    }

}

package com.melodymatebackend.music.application.dto;

import com.melodymatebackend.music.domain.Music;
import com.melodymatebackend.music.domain.ViewCount;

public record ViewCountDto(
        Music music,
        String viewCount
) {

    public ViewCount toEntity(Music music, String viewCount) {
        return ViewCount.builder()
                .music(music)
                .viewCount(viewCount)
                .build();
    }
}

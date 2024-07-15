package com.melodymatebackend.music.application.dto;

import com.melodymatebackend.music.domain.Music;
import com.melodymatebackend.music.domain.ViewCount;
import java.time.LocalDate;

public record ViewCountDto(
    Music music,
    String viewCount
) {

    public ViewCount toEntity() {
        return ViewCount.builder()
            .music(this.music)
            .viewCount(this.viewCount)
            .rankDate(LocalDate.now())
            .build();
    }
}

package com.melodymatebackend.music.application.dto;

import com.melodymatebackend.music.domain.Music;
import com.melodymatebackend.music.domain.Ranking;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class RankingDto {
    private LocalDate rankDate;
    private String rank;
    private Music music;

    public RankingDto(Ranking o) {
        rankDate = o.getRankDate();
        rank = o.getRank();
        music = o.getMusic();
    }

    public Ranking toEntity() {
        return Ranking.builder()
                .rankDate(rankDate)
                .rank(rank)
                .music(music)
                .build();
    }
}
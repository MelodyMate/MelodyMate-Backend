package com.melodymatebackend.music.application.dto;

import com.melodymatebackend.music.domain.Music;
import com.melodymatebackend.music.domain.Ranking;
import lombok.*;
import org.springframework.data.domain.jaxb.SpringDataJaxb;

import java.time.LocalDate;

@Getter
@Setter
public class RankingDTO {
    private LocalDate rankDate;
    private String rank;
    private Music music;

    public Ranking toEntity() {
        return Ranking.builder()
                .rankDate(rankDate)
                .rank(rank)
                .music(music)
                .build();
    }
}
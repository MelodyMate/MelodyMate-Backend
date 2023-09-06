package com.melodymatebackend.music.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Table(name = "MUSIC")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Entity
public class Music {
    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "RANKING", nullable = false)
    private String ranking;

    @Column(name = "URL", nullable = true, length = 500)
    private String url;

    @Column(name = "MUSICTITLE", nullable = false, length = 200)
    private String musicTitle;

    @Column(name = "ARTIST", nullable = true, length = 50)
    private String artist;

    @Column(name = "THUMBNAIL", nullable = true, length = 300)
    private String thumbnail;

    @Column(name = "DURATION", nullable = true, length = 20)
    private String duration;

    @Column(name = "VIEWCOUNT", nullable = false, length = 15)
    private String viewCount;

    @Column(name = "RELEASEDATE", nullable = false, length = 15)
    private String releaseDate;

    @Column(name = "RANKDATE", nullable = true)
    private LocalDate rankDate;

}
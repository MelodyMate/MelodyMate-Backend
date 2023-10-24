package com.melodymatebackend.music.domain;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Entity
@Table(name = "MUSIC")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Music {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "url", nullable = false, length = 500)
    private String url;

    @Column(name = "title", nullable = false, length = 200)
    private String title;

    @Column(name = "artist", nullable = false, length = 50)
    private String artist;

    @Column(name = "thumbnail", nullable = false, length = 300)
    private String thumbnail;

    @Column(name = "duration", nullable = false, length = 20)
    private String duration;

    @Column(name = "viewCount", nullable = false, length = 15)
    private String viewCount;

    @Column(name = "releaseDate", nullable = false, length = 15)
    private String releaseDate;

}
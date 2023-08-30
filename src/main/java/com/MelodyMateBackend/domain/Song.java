package com.MelodyMateBackend.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "SONGS")
public class Song {
    @Id
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "RANK", nullable = false)
    private Boolean rank = false;

    @Column(name = "URL", nullable = false, length = 500)
    private String url;

    @Column(name = "SONGTITLE", nullable = false, length = 200)
    private String songtitle;

    @Column(name = "ARTIST", nullable = false, length = 50)
    private String artist;

    @Column(name = "THUMBNAIL", nullable = false, length = 300)
    private String thumbnail;

    @Column(name = "DURATION", nullable = false, length = 20)
    private String duration;

    @Column(name = "VIEWCOUNT", nullable = false, length = 15)
    private String viewcount;

    @Column(name = "RELEASEDATE", nullable = false, length = 15)
    private String releasedate;

    @Column(name = "RANKDATE", nullable = false)
    private LocalDate rankdate;

}
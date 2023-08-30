package com.MelodyMateBackend.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "SONGLIST")
public class Songlist {
    @Id
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "SONGSEQUENCE", nullable = false)
    private Boolean songsequence = false;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "SONGS_ID", nullable = false)
    private Song songs;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "PLAYLIST_ID", nullable = false)
    private Playlist playlist;

}
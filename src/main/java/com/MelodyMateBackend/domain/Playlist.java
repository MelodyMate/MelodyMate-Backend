package com.MelodyMateBackend.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "PLAYLIST")
public class Playlist {
    @Id
    @Column(name = "ID", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "USERS_ID", nullable = false)
    private User users;

    @Column(name = "CREATEDATE", nullable = false)
    private Instant createdate;

    @Column(name = "UPDATEDATE")
    private Instant updatedate;

}
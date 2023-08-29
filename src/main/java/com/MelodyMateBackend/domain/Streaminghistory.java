package com.MelodyMateBackend.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "STREAMINGHISTORY")
public class Streaminghistory {
    @Id
    @Column(name = "ID", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "USERS_ID", nullable = false)
    private User users;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "SONGS_ID", nullable = false)
    private Song songs;

    @Column(name = "CREATEDATE", nullable = false)
    private Instant createdate;

}
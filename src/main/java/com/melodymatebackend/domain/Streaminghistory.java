package com.melodymatebackend.domain;

import com.melodymatebackend.users.domain.Users;
import com.melodymatebackend.music.domain.Music;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "USERS_ID", nullable = false)
    private Users users;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "MUSIC_ID", nullable = false)
    private Music music;

    @Column(name = "CREATEDATE", nullable = false)
    private Instant createdate;

}
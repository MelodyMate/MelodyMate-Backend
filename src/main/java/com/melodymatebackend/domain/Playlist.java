package com.melodymatebackend.domain;

import com.melodymatebackend.users.domain.Users;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "USERS_ID", nullable = false)
    private Users users;

    @Column(name = "CREATEDATE", nullable = false)
    private Instant createdate;

    @Column(name = "UPDATEDATE")
    private Instant updatedate;

}
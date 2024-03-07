package com.melodymatebackend.domain;

import com.melodymatebackend.users.domain.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Getter
@Entity
@Table(name = "PLAYLIST")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Playlist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, columnDefinition = "NUMERIC(19, 0)")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "users_id", nullable = false, columnDefinition = "NUMERIC(19, 0)")
    private User users;

    @Column(name = "createDate", nullable = false)
    private Instant createDate;

    @Column(name = "updateDate")
    private Instant updateDate;

}
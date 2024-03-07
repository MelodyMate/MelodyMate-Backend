package com.melodymatebackend.domain;

import com.melodymatebackend.music.domain.Music;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Entity
@Table(name = "MUSICLIST")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Musiclist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, columnDefinition = "NUMERIC(19, 0)")
    private Long id;

    @Column(name = "songsequence", nullable = false)
    private Integer songsequence;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "music_id", nullable = false, columnDefinition = "NUMERIC(19, 0)")
    private Music music;

}
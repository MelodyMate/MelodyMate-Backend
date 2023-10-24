package com.melodymatebackend.domain;

import com.melodymatebackend.music.domain.Music;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Entity
@Table(name = "MIXTAPES_IN_MUSIC")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MixtapesInMusic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "position", nullable = false)
    private Integer position;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "mixtapes_id", nullable = false)
    private Mixtape mixtapes;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "music_id", nullable = false)
    private Music music;

}
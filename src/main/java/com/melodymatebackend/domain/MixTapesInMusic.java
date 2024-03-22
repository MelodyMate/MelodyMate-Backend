package com.melodymatebackend.domain;

import com.melodymatebackend.common.domain.BaseEntity;
import com.melodymatebackend.music.domain.Music;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MixTapesInMusic extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, columnDefinition = "NUMERIC(19, 0)")
    private Long id;

    @Column(name = "position", nullable = false)
    private Integer position;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "mixtapes_id", nullable = false, columnDefinition = "NUMERIC(19, 0)")
    private MixTape mixtapes;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "music_id", nullable = false, columnDefinition = "NUMERIC(19, 0)")
    private Music music;

}
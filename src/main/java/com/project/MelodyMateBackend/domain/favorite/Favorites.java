package com.project.MelodyMateBackend.domain.favorite;

import com.project.MelodyMateBackend.domain.BaseTimeEntity;
import com.project.MelodyMateBackend.domain.favoritesong.FavoriteSong;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Favorites extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String userId;

    @Column
    private String name;

    // 삭제 시 관계테이블 요소도 연쇄삭제
    @OneToMany(mappedBy = "favorites", cascade = CascadeType.REMOVE)
    private List<FavoriteSong> favoriteSongList;

}

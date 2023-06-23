package com.project.MelodyMateBackend.domain.favoritesong;

import com.project.MelodyMateBackend.domain.BaseTimeEntity;
import com.project.MelodyMateBackend.domain.Song.Song;
import com.project.MelodyMateBackend.domain.favorite.Favorites;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class FavoriteSong extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "FAVORITES_ID")
    private Favorites favorites;

    @ManyToOne
    @JoinColumn(name = "SONG_ID")
    private Song song;

    @Builder
    public FavoriteSong(Favorites favorites, Song song){
        this.favorites = favorites;
        this.song = song;
    }

}

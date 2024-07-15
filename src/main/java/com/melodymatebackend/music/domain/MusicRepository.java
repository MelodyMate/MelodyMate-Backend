package com.melodymatebackend.music.domain;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MusicRepository extends JpaRepository<Music, Long> {

    Optional<Music> findByArtistAndTitle(String artist, String title);

    boolean existsByArtistAndTitle(String artist, String title);

}

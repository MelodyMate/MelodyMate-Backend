package com.melodymatebackend.music.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MusicRepository extends JpaRepository<Music, Long> {

    Music findByArtistAndTitle(String artist, String title);

    boolean existsByArtistAndTitle(String artist, String title);



}

package com.melodymatebackend.music.domain;

import com.melodymatebackend.music.domain.Music;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface MusicRepository extends JpaRepository<Music, Long> {

    List<Music> findAllByRankDate(LocalDate rankDate);
}

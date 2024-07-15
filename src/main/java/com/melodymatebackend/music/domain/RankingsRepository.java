package com.melodymatebackend.music.domain;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RankingsRepository extends JpaRepository<Ranking, Long> {

    List<Ranking> findByRankDate(LocalDate rankDate, Sort id);

    @EntityGraph(attributePaths = "music")
    List<Ranking> findByRankDateOrderByIdAsc(LocalDate rankDate);

    void deleteRankingByRankDate(LocalDate rankDate);

    @EntityGraph(attributePaths = "music")
    List<Ranking> findByRankDate(LocalDate rankDate);
}

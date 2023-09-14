package com.melodymatebackend.repository;

import com.melodymatebackend.domain.Musiclist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaylistSongRelationRepository extends JpaRepository<Musiclist, Long> {
}

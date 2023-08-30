package com.MelodyMateBackend.repository;

import com.MelodyMateBackend.domain.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaylistSongRelationRepository extends JpaRepository<PlaylistSongRelationRepository, Long> {
}

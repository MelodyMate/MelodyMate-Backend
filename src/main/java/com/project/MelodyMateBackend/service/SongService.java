package com.project.MelodyMateBackend.service;

import com.project.MelodyMateBackend.domain.Song.Song;
import com.project.MelodyMateBackend.domain.Song.SongRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class SongService {
    private final SongRepository songRepository;


    @Transactional(readOnly = true)
    public List<Song> findAllDesc(){
        return songRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
    }
}

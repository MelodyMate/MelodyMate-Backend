package com.melodymatebackend.music.controller;

import com.melodymatebackend.music.application.MusicService;
import com.melodymatebackend.music.domain.Music;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1.0")
public class MusicController {

    private final MusicService musicService;

    // chart API 전달
    @GetMapping("/chart")
    public ResponseEntity<Map<String, Object>> save() {
        LocalDate rankDate = LocalDate.now();
        List<Music> music = musicService.musicList(rankDate);
        Map<String, Object> musicList = new LinkedHashMap<>();



        musicList.put("count", music.size());
        musicList.put("data", music);
        return ResponseEntity.ok().body(musicList);
    }


}

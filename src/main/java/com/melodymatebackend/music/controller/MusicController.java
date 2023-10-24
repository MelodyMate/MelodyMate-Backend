package com.melodymatebackend.music.controller;

import com.melodymatebackend.music.application.CrawlingService;
import com.melodymatebackend.music.application.MusicService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1.0")
public class MusicController {

    private final MusicService musicService;
    private final CrawlingService crawlingService;

    // chart API 전달
    @GetMapping("/chart")
    public ResponseEntity<Map<String, Object>> musicList(@RequestParam(required = false) LocalDate date) {
        if(date == null){
            date = LocalDate.now();
        }

        List<Map<String, Object>> musicList = musicService.getMusicList(date);
        Map<String, Object> musicData = new LinkedHashMap<>();

        musicData.put("count", musicList.size());
        musicData.put("data", musicList);

        // TODO : 랭킹이 하나만 나옵니다...
        return ResponseEntity.ok(musicData);
    }

    @GetMapping("/admin")
    public void musicSave() throws InterruptedException {
        crawlingService.crawlingMain();
    }

}

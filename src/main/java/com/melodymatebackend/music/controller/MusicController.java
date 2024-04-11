package com.melodymatebackend.music.controller;

import com.melodymatebackend.music.application.CrawlingService;
import com.melodymatebackend.music.application.MusicService;
import com.melodymatebackend.music.application.dto.RankingDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class MusicController {

    private final MusicService musicService;
    private final CrawlingService crawlingService;

    // chart API 전달
    @GetMapping("/v1.0/chart")
    public ResponseEntity<Map<String, Object>> musicList(@RequestParam(value = "date",required = false) LocalDate date) {
        List<Map<String, Object>> musicList = musicService.getMusicList(date);
        Map<String, Object> musicData = new LinkedHashMap<>();

        musicData.put("count", musicList.size());
        musicData.put("data", musicList);

        return ResponseEntity.ok(musicData);
    }

    @GetMapping("/v1.1/chart")
    public ResponseEntity<List<RankingDto>> newMusicList(@RequestParam(value = "date",required = false) LocalDate date) {
        List<RankingDto> findRankDto = musicService.newGetMusicList(date);
        return ResponseEntity.ok(findRankDto);
    }


    @GetMapping("/admin")
    public void musicSave() throws InterruptedException, IOException {
        crawlingService.crawlingMain();
    }

}

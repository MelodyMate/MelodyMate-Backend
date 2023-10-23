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

        return ResponseEntity.ok(null);
    }

    @GetMapping("/admin")
    public void musicSave() throws InterruptedException {
        crawlingService.crawlingMain();
    }

}

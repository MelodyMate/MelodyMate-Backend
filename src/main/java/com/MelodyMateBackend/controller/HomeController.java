package com.MelodyMateBackend.controller;

import com.MelodyMateBackend.domain.Song;
import com.MelodyMateBackend.service.CrawlingService;
import com.MelodyMateBackend.service.SongService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class HomeController {

    private final SongService songService;
    private final CrawlingService crawlingService;

    // chart API 전달
    @GetMapping("/chart")
    public ResponseEntity<List<Song>> save() throws InterruptedException {

        return new ResponseEntity<>(songService.getAllSong(), HttpStatus.OK);
    }


}

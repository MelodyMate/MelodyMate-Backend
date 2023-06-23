package com.project.MelodyMateBackend.controller;

import com.project.MelodyMateBackend.domain.Song.Song;
import com.project.MelodyMateBackend.service.CrawlingService;
import com.project.MelodyMateBackend.service.SongService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class HomeController {

    private final SongService songService;
    private final CrawlingService crawlingService;


    @GetMapping("/")
    public List<Song> Home() {

        crawlingService.dataCrawling();
        return songService.findAllDesc();
    }

}

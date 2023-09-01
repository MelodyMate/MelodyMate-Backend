package com.melodymatebackend.music.presentation;

import com.melodymatebackend.music.application.MusicService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1.0")
public class MusicController {

    private final MusicService musicService;

    // chart API 전달
    @GetMapping("/chart")
    public String save() {


        return "test Hello";
    }


}

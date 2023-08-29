package com.MelodyMateBackend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class HomeController {
    // chart API 전달
    @GetMapping("/chart")
    public String save(){
        return "test Hello";
    }


}

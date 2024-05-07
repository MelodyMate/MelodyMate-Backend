package com.melodymatebackend.auth.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("")
@RequiredArgsConstructor
public class AuthController {

    public static final int COOKIE_AGE_SECONDS = 604800;

    @PostMapping("/oauth2/authorization/{provider}")
    public ResponseEntity<String> loginRedirect(@PathVariable(name = "provider") String provider) {

        log.info(provider);
        return ResponseEntity.ok("login success");
    }

    @GetMapping("/login/oauth2/code/{provider}")
    public ResponseEntity<String> login(@PathVariable(name = "provider") String provider,
        @RequestParam(name = "code") String code) {

        log.info("code = ");
        log.info(code);
        return ResponseEntity.ok("omg");
    }

}

package com.melodymatebackend.auth.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
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

        return ResponseEntity.ok("omg");
    }

    @GetMapping("/login/users")
    public String getUserInfo(@AuthenticationPrincipal UserDetails userDetails) {
        return userDetails.getUsername();
    }


    @GetMapping
    public String mail() {
        return "{test : test}";
    }

}

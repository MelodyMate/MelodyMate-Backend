package com.melodymatebackend.auth.controller;

import com.melodymatebackend.auth.service.CustomOAuth2UserService;
import com.melodymatebackend.auth.user.CustomOAuth2User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping
@RequiredArgsConstructor
public class AuthController {

    public static final int COOKIE_AGE_SECONDS = 604800;
    private final CustomOAuth2UserService service;

    @PostMapping("/oauth2/authorization/{provider}")
    public ResponseEntity<String> loginRedirect(@PathVariable(name = "provider") String provider) {
        log.info("provider === {}", provider);

        return ResponseEntity.ok("login success");
    }

    @GetMapping("/login/oauth2/code/{provider}")
    public ResponseEntity<String> login(@PathVariable(name = "provider") String provider,
        @RequestParam(name = "code") String code) {
        log.info("code === {}", code);
        return ResponseEntity.ok("{\"loginCode\" : \"" + code + "\"}");
    }

    @PostMapping("login/oauth2/code/{provider}")
    public ResponseEntity<String> loginRedirect(@PathVariable(name = "provider") String provider,
        @RequestParam(name = "code") String code) {

        log.info("### loginRedirect === {}", code);
        log.info("###loginRedirect code === {}", code);

        return ResponseEntity.ok("{\"loginCode\" : \"" + code + "\"}");
    }

    @GetMapping("/login/users")
    public String getUserInfo(@AuthenticationPrincipal CustomOAuth2User userDetails) {
        return userDetails.getName();
    }

    @GetMapping("/auth/success")
    public String mail(String accessToken) {
        log.info("token === {}", accessToken);

        return "{\"test\" : \"test\"}";
    }

    @GetMapping("/logout/{provider}")
    public String logout(@PathVariable String provider,
        @AuthenticationPrincipal CustomOAuth2User userDetails) {
        log.info("logout === {}", provider);
        log.info("logout users === {}", userDetails);
        return "{\"logout\" : \"success\"}";
    }


}

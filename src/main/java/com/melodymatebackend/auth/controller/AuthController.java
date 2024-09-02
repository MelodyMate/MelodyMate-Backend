package com.melodymatebackend.auth.controller;

import com.melodymatebackend.auth.service.CustomOAuth2Service;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@Slf4j
@RestController
@RequestMapping
@RequiredArgsConstructor
public class AuthController {

    private final CustomOAuth2Service customOAuth2Service;

    @Value("${spring.security.oauth2.client.registration.kakao.admin-key}")
    private String adminKey;

    @GetMapping("/users")
    public String getUserInfo(@AuthenticationPrincipal UserDetails userDetails) {
        return userDetails.getUsername();
    }

    @GetMapping("/logout/{provider}")
    public void logout(@AuthenticationPrincipal UserDetails userDetails,
        @PathVariable String provider, HttpServletResponse response) throws IOException {

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "KakaoAK " + adminKey);

        MultiValueMap<String, String> body = new LinkedMultiValueMap<String, String>();

        body.add("target_id_type", "user_id");
        body.add("target_id", userDetails.getUsername());

        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(body, headers);

        RestTemplate rt = new RestTemplate();
        rt.postForEntity(
            "https://kapi.kakao.com/v1/user/logout",
            entity,
            String.class
        );

        deleteCookie(response);

        response.sendRedirect("https://melodymate.netlify.app/");

    }

    private void deleteCookie(HttpServletResponse response) {
        ResponseCookie cookie = ResponseCookie.from("Authorization")
            .maxAge(0)
            .path("/")
            .build();

        response.setHeader("Set-Cookie", cookie.toString());

    }


}

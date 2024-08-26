package com.melodymatebackend.auth.service;

import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@RequiredArgsConstructor
@Service
public class KakaoOauthService {

    private final CustomOAuth2UserService service;

    public Map<String, Object> getUserToken(String accessToken) {
        return WebClient.create()
            .get()
            .uri("https://kapi.kakao.com/v2/user/me")
            .headers(http -> http.setBearerAuth(accessToken))
            .retrieve()
            .bodyToMono(new ParameterizedTypeReference<Map<String, Object>>() {
            })
            .block();
    }

    
}

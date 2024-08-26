package com.melodymatebackend.auth.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.melodymatebackend.auth.jwt.TokenProvider;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class CustomSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final TokenProvider tokenProvider;
    private static final String URI = "https://melodymate.netlify.app/";
    private ObjectMapper objectMapper = new ObjectMapper();


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
        Authentication authentication) throws IOException, ServletException {
        log.info("successful authentication");
        // accessToken, refreshToken 발급
        String accessToken = tokenProvider.generateAccessToken(authentication);

//        tokenProvider.generateRefreshToken(authentication, accessToken);

        response.addCookie(createCookie("Authorization", accessToken));

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

//        TokenDTO tokenDTO = new TokenDTO(accessToken);
//
//        String result = objectMapper.writeValueAsString(tokenDTO);
//
        response.sendRedirect(URI);
    }

    private Cookie createCookie(String key, String value) {
        Cookie cookie = new Cookie(key, value);
        cookie.setMaxAge(60 * 60 * 60);
        cookie.setPath("/");
        cookie.setHttpOnly(true);

        return cookie;
    }

}

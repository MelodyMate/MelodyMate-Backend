package com.melodymatebackend.auth.hanlder;

import com.melodymatebackend.auth.jwt.utils.TokenProvider;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseCookie;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class CustomSuccessHandler implements AuthenticationSuccessHandler {

    private static final String URI = "https://melodymate.netlify.app/";
    private final TokenProvider tokenProvider;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
        Authentication authentication) throws IOException, ServletException {

        String accessToken = tokenProvider.generateAccessToken(authentication);

        tokenSetCookie(response, accessToken);
        response.sendRedirect(URI);
    }

    private void tokenSetCookie(HttpServletResponse response, String accessToken) {
        ResponseCookie cookie = ResponseCookie.from("Authorization", accessToken)
            .path("/")
            .secure(true)
            .sameSite("None")
            .httpOnly(true)
            .maxAge(60 * 60 * 24)
            .build();

        response.setHeader("Set-Cookie", cookie.toString());
    }


}

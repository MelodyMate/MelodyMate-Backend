package com.melodymatebackend.auth.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@RequiredArgsConstructor
@Component
@Slf4j
public class JWTFilter extends OncePerRequestFilter {

    private final String BEARER_PREFIX = "Bearer ";
    private final TokenProvider tokenProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
        FilterChain filterChain) throws ServletException, IOException {

        log.info("JWT Filter");
//        String accessToken = resolveToken(request);
//
//        try {
//
//            if (tokenProvider.validateToken(accessToken)) {
//                setAuthentication(accessToken);
//            } else {
//                // 만료되었을 경우 accessToken 재발급
//                String reissueAccessToken = tokenProvider.reissueAccessToken(accessToken);
//
//                if (StringUtils.hasText(reissueAccessToken)) {
//                    setAuthentication(reissueAccessToken);
//
//                    // 재발급된 accessToken 다시 전달
//                    response.setHeader(AUTHORIZATION, BEARER_PREFIX + reissueAccessToken);
//                }
//            }
//        } catch (UsernameNotFoundException e) {
//            e.printStackTrace();
//        }

        filterChain.doFilter(request, response);
    }

    private void setAuthentication(String accessToken) {
        Authentication authentication = tokenProvider.getAuthentication(accessToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private String resolveToken(HttpServletRequest request) {
        // TODO : 추후에 헤더에 담아서 오는걸로 변경 해야함.
//        String token = request.getHeader(AUTHORIZATION);
        String authorization = null;
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("Authorization")) {
                authorization = cookie.getValue();
            }
        }
        return authorization;
    }

}

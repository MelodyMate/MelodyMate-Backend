package com.melodymatebackend.auth.service;


import com.melodymatebackend.auth.dto.CustomOAuth2User;
import com.melodymatebackend.auth.dto.UserDTO;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

@RequiredArgsConstructor
@Slf4j
public class JWTFilter extends OncePerRequestFilter {

    private final JWTUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
        FilterChain filterChain) throws ServletException, IOException {

        log.info("ha...");
        String authorization = null;
        Cookie[] cookies = request.getCookies();

        for (Cookie cookie : cookies) {
            log.info("cookie..." + cookie.getName());
            if (cookie.getName().equals("Authorization")) {
                authorization = cookie.getValue();
            }
        }

//        if (authorization == null) {
//            log.info("token null!");
//            filterChain.doFilter(request, response);
//            return;
//        }

        String token = authorization;

        if (jwtUtil.isTokenExpired(token)) {
            filterChain.doFilter(request, response);
            return;
        }

        String username = jwtUtil.getUsername(token);
        String role = jwtUtil.getRole(token);

        UserDTO userDTO = new UserDTO(username, null, role);
        CustomOAuth2User customOauth2User = new CustomOAuth2User(userDTO);

        Authentication authToken = new UsernamePasswordAuthenticationToken(customOauth2User, null,
            customOauth2User.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(authToken);

        filterChain.doFilter(request, response);
    }
}

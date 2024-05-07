package com.melodymatebackend.auth;

import com.melodymatebackend.auth.service.JWTUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CustomFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    private final JWTUtil jwtUtil;

    public CustomFailureHandler(JWTUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
        AuthenticationException exception) throws IOException, ServletException {

        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        log.info("로그인에 실패하였습니다.");
    }
}


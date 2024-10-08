package com.melodymatebackend.auth.jwt.utils;

import static org.springframework.security.oauth2.core.OAuth2ErrorCodes.INVALID_TOKEN;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.crypto.SecretKey;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Slf4j
@RequiredArgsConstructor
@Component
public class TokenProvider {

    private static final long ACCESS_TOKEN_EXPIRE_TIME = 1000 * 60 * 30L;
    private static final long REFRESH_TOKEN_EXPIRE_TIME = 1000 * 60 * 60L * 24 * 7;
    private static final String KEY_ROLE = "role";

    @Value("${spring.jwt.secret}")
    private String secret;
    private SecretKey secretKey;

    @PostConstruct
    private void setSecretKey() {
        secretKey = Keys.hmacShaKeyFor(secret.getBytes());
    }


    public String generateAccessToken(Authentication authentication) {
        return generateToken(authentication);
    }

    // 1. refresh token 발급
    public void generateRefreshToken(Authentication authentication, String accessToken) {
        String refreshToken = generateToken(authentication);
//        tokenService.saveOrUpdate(authentication.getName(), refreshToken, accessToken); // redis에 저장
    }

    private String generateToken(Authentication authentication) {
        Date now = new Date();
        Date expiredDate = new Date(now.getTime() + ACCESS_TOKEN_EXPIRE_TIME);

        String authorities = authentication.getAuthorities().stream()
            .map(GrantedAuthority::getAuthority)
            .collect(Collectors.joining());

        return Jwts.builder()
            .subject(authentication.getName())
            .claim(KEY_ROLE, authorities)
            .issuedAt(now)
            .expiration(expiredDate)
            .signWith(secretKey, Jwts.SIG.HS512)
            .compact();
    }

    public Authentication getAuthentication(String token) {
        Claims claims = parseClaims(token);
        List<SimpleGrantedAuthority> authorities = getAuthorities(claims);

        // 2. security의 User 객체 생성
        User principal = new User(claims.getSubject(), "", authorities);
        return new UsernamePasswordAuthenticationToken(principal, token, authorities);
    }

    private List<SimpleGrantedAuthority> getAuthorities(Claims claims) {
        return Collections.singletonList(new SimpleGrantedAuthority(
            claims.get(KEY_ROLE).toString()));
    }

    // 3. accessToken 재발급
    public String reissueAccessToken(String accessToken) {
        if (StringUtils.hasText(accessToken)) {
            return generateAccessToken(getAuthentication(accessToken));
        }
        return null;
    }

    public boolean validateToken(String token) {
        if (!StringUtils.hasText(token)) {
            return false;
        }

        Claims claims = parseClaims(token);
        return claims.getExpiration().after(new Date());
    }

    private Claims parseClaims(String token) {
        try {
            return Jwts.parser().verifyWith(secretKey).build()
                .parseSignedClaims(token).getPayload();
        } catch (ExpiredJwtException e) {
            return e.getClaims();
        } catch (MalformedJwtException e) {
            throw new IllegalArgumentException(INVALID_TOKEN);
        } catch (SecurityException e) {
            throw new IllegalArgumentException(INVALID_TOKEN + "JWT");
        }
    }


}

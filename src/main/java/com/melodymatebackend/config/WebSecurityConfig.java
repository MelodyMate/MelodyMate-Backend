package com.melodymatebackend.config;

import com.melodymatebackend.auth.CustomSuccessHandler;
import com.melodymatebackend.auth.service.CustomOAuth2UserService;
import com.melodymatebackend.auth.service.JWTUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    private final CustomOAuth2UserService customOAuth2UserService;
    private final CustomSuccessHandler customSuccessHandler;
    private final JWTUtil jwtUtil;

    public WebSecurityConfig(CustomOAuth2UserService customOAuth2UserService,
        CustomSuccessHandler customSuccessHandler, JWTUtil jwtUtil) {
        this.customOAuth2UserService = customOAuth2UserService;
        this.customSuccessHandler = customSuccessHandler;
        this.jwtUtil = jwtUtil;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .cors(cors -> cors.disable())

            .authorizeHttpRequests(request -> request
                .requestMatchers("/**").permitAll()
            )

            .formLogin((login) -> login.disable())

            .httpBasic((httpBasic) -> httpBasic.disable())

            .sessionManagement((session) -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))

//            .addFilterBefore(new JWTFilter(jwtUtil), UsernamePasswordAuthenticationFilter.class)

            .oauth2Login((oauth2) -> oauth2
                .userInfoEndpoint((userInfoEndpointConfig) -> userInfoEndpointConfig
                    .userService(customOAuth2UserService))
                .successHandler(customSuccessHandler))

            .logout(customizer -> customizer
                .logoutUrl("/api/logout")
                .logoutSuccessUrl("/")
                .permitAll()
            );

        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

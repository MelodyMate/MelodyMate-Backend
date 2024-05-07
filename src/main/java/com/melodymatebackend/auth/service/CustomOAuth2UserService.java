package com.melodymatebackend.auth.service;

import com.melodymatebackend.auth.dto.CustomOAuth2User;
import com.melodymatebackend.auth.dto.KakaoResponse;
import com.melodymatebackend.auth.dto.OAuth2Response;
import com.melodymatebackend.auth.dto.UserDTO;
import com.melodymatebackend.users.domain.User;
import com.melodymatebackend.users.domain.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private static final Logger log = LoggerFactory.getLogger(CustomOAuth2UserService.class);
    private final UsersRepository usersRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        log.info("CustomOAuth2UserService loadUser");
        OAuth2User oAuth2User = super.loadUser(userRequest);

        OAuth2Response oAuth2Response = null;

        String registerId = userRequest.getClientRegistration().getRegistrationId();
        log.info("login +++" + oAuth2User.getAttributes());

        if (registerId.equals("kakao")) {
            oAuth2Response = new KakaoResponse(oAuth2User.getAttributes());
        }

        String username = oAuth2Response.getProvider() + " " + oAuth2Response.getProviderId();

        User existDate = usersRepository.findByUsername(username);

        if (existDate == null) {
            User buildUser = User.builder()
                .username(username)
                .email(oAuth2Response.getEmail())
                .nickname(oAuth2Response.getNickname())
                .picture(oAuth2Response.getPicture())
                .role("ROLE_USER")
                .build();
            usersRepository.save(buildUser);

            UserDTO userDTO = new UserDTO(username, oAuth2Response.getNickname(), "ROLE_USER");

            return new CustomOAuth2User(userDTO);

        } else {

            User buildUser = existDate.builder()
                .email(existDate.getEmail())
                .nickname(existDate.getNickname())
                .picture(existDate.getPicture())
                .role(existDate.getRole())
                .build();
            usersRepository.save(buildUser);

            UserDTO userDTO = new UserDTO(username, oAuth2Response.getNickname(), "ROLE_USER");

            return new CustomOAuth2User(userDTO);
        }

    }
}

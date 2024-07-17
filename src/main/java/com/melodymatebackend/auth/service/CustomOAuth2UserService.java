package com.melodymatebackend.auth.service;

import com.melodymatebackend.auth.user.CustomOAuth2User;
import com.melodymatebackend.auth.user.KakaoResponse;
import com.melodymatebackend.auth.user.OAuth2Response;
import com.melodymatebackend.auth.user.UserDTO;
import com.melodymatebackend.users.domain.User;
import com.melodymatebackend.users.domain.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final UsersRepository usersRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        OAuth2User oAuth2User = super.loadUser(userRequest);
        System.out.println(oAuth2User);

        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        OAuth2Response oAuth2Response = null;

        if (registrationId.equals("kakao")) {
            oAuth2Response = new KakaoResponse(oAuth2User.getAttributes());
        } else {
            return null;
        }

        String username = oAuth2Response.getProvider() + " " + oAuth2Response.getProviderId();
        User existData = usersRepository.findByUsername(username);

        if (existData == null) {

            User userEntity = User.builder()
                .username(username)
                .email(oAuth2Response.getEmail())
                .nickname(oAuth2Response.getName())
                .role("ROLE_USER")
                .imageUrl(oAuth2Response.getImageUrl())
                .build();

            usersRepository.save(userEntity);

            UserDTO userDTO = new UserDTO();
            userDTO.setUsername(username);
            userDTO.setName(oAuth2Response.getName());
            userDTO.setRole("ROLE_USER");

            return new CustomOAuth2User(userDTO);
        } else {

            existData.dataUpdate(oAuth2Response, existData);

            usersRepository.save(existData);

            UserDTO userDTO = new UserDTO();
            userDTO.setUsername(existData.getUsername());
            userDTO.setName(oAuth2Response.getName());
            userDTO.setRole(existData.getRole());
            userDTO.setImageUrl(existData.getImageUrl());

            return new CustomOAuth2User(userDTO);
        }
    }
}
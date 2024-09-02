package com.melodymatebackend.auth.service;

import com.melodymatebackend.auth.domain.OAuth2UserInfo;
import com.melodymatebackend.auth.domain.UserDTO;
import com.melodymatebackend.users.domain.User;
import com.melodymatebackend.users.domain.UsersRepository;
import jakarta.servlet.http.HttpSession;
import java.util.Collections;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomOAuth2Service extends DefaultOAuth2UserService {

    private final UsersRepository usersRepository;
    private final HttpSession httpSession;


    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        // 현재 로그인 진행 중인 서비스를 구분하는 코드 (네이버 로그인인지 구글 로그인인지 구분)
        String registrationId = userRequest.getClientRegistration().getRegistrationId();

        // OAuth2 로그인 진행 시 키가 되는 필드 값 (Primary Key와 같은 의미)을 의미
        // 구글의 기본 코드는 "sub", 후에 네이버 로그인과 구글 로그인을 동시 지원할 때 사용
        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails()
            .getUserInfoEndpoint().getUserNameAttributeName();

        // OAuth2UserService를 통해 가져온 OAuthUser의 attribute를 담을 클래스 ( 네이버 등 다른 소셜 로그인도 이 클래스 사용)
        OAuth2UserInfo attributes = OAuth2UserInfo.of(registrationId, userNameAttributeName,
            oAuth2User.getAttributes());

        User userEntity = getOrSave(attributes);

        httpSession.setAttribute("user",
            new UserDTO(userEntity)); // UserDTO : 세션에 사용자 정보를 저장하기 위한 Dto 클래스

        return new DefaultOAuth2User(
            Collections.singleton(new SimpleGrantedAuthority(userEntity.getRoleKey())),
            attributes.getAttributes(),
            attributes.getNameAttributeKey());

    }

    private User getOrSave(OAuth2UserInfo oAuth2UserInfo) {
        User user = usersRepository.findByEmail(oAuth2UserInfo.getEmail())
            .map(
                entity -> entity.update(oAuth2UserInfo.getNickname(), oAuth2UserInfo.getImageUrl()))
            .orElse(oAuth2UserInfo.toEntity());
        
        return usersRepository.save(user);
    }

}

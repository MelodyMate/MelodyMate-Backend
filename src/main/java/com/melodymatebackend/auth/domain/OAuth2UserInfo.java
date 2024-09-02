package com.melodymatebackend.auth.domain;

import com.melodymatebackend.common.exception.AuthException;
import com.melodymatebackend.common.exception.ExceptionCode;
import com.melodymatebackend.users.domain.User;
import java.util.Map;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
public class OAuth2UserInfo {

    private Map<String, Object> attributes;
    private String nameAttributeKey;
    private String nickname;
    private String email;
    private String imageUrl;
    private ProviderType providerType;
    private String providerId;


    @Builder
    public OAuth2UserInfo(Map<String, Object> attributes, String nameAttributeKey, String nickname,
        String email, String imageUrl, ProviderType providerType, String providerId) {
        this.attributes = attributes;
        this.nameAttributeKey = nameAttributeKey;
        this.nickname = nickname;
        this.email = email;
        this.imageUrl = imageUrl;
        this.providerType = providerType;
        this.providerId = providerId;
    }

    public static OAuth2UserInfo of(String registrationId, String userNameAttributeName,
        Map<String, Object> attributes) {
        return switch (registrationId) { // registration id별로 userInfo 생성
            case "kakao" -> ofKakao(registrationId, userNameAttributeName, attributes);
            case "google" -> ofGoogle(attributes);
            default -> throw new AuthException(ExceptionCode.NOT_FOUND_MEMBER_ID);
        };
    }

    private static OAuth2UserInfo ofGoogle(Map<String, Object> attributes) {
        return OAuth2UserInfo.builder()
            .nickname((String) attributes.get("name"))
            .email((String) attributes.get("email"))
            .imageUrl((String) attributes.get("picture"))
            .build();
    }

    private static OAuth2UserInfo ofKakao(String registrationId, String userNameAttributeName,
        Map<String, Object> attributes) {

        Map<String, Object> account = (Map<String, Object>) attributes.get("kakao_account");
        Map<String, Object> profile = (Map<String, Object>) account.get("profile");

        return OAuth2UserInfo.builder()
            .nickname((String) profile.get("nickname"))
            .email((String) account.get("email"))
            .imageUrl((String) profile.get("profile_image_url"))
            .attributes(attributes)
            .providerType(ProviderType.KAKAO)
            .providerId(String.valueOf(attributes.get("id")))
            .nameAttributeKey(userNameAttributeName)
            .build();
    }

    public User toEntity() {
        return User.builder()
            .password("password")
            .nickname(nickname)
            .email(email)
            .imageUrl(imageUrl)
            .role(RoleType.USER)
            .providerType(providerType)
            .providerId(providerId)
            .build();
    }
}

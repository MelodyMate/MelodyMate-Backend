package com.melodymatebackend.auth.dto;

import java.util.Map;

public class KakaoResponse implements OAuth2Response {

    private Map<String, Object> attribute;
    private Map<String, Object> kakaoAccountAttributes;
    private Map<String, Object> profileAttributes;

    private final String PROVIDER = "kakao";

    public KakaoResponse(Map<String, Object> attribute) {
        this.attribute = attribute;
        this.kakaoAccountAttributes = (Map<String, Object>) attribute.get("kakao_account");
        this.profileAttributes = (Map<String, Object>) attribute.get("profile");
    }

    @Override
    public String getProvider() {
        return PROVIDER;
    }

    @Override
    public String getProviderId() {
        return attribute.get("id").toString();
    }

    @Override
    public String getEmail() {
        return kakaoAccountAttributes.get("email").toString();
    }

    @Override
    public String getNickname() {
        return kakaoAccountAttributes.get("nickname").toString();
    }

    @Override
    public String getPicture() {
        return kakaoAccountAttributes.get("picture").toString();
    }
}

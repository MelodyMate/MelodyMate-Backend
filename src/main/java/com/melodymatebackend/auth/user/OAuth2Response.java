package com.melodymatebackend.auth.user;

import java.util.Map;

public interface OAuth2Response {

    // 제공자(kakao, naver, google...)
    String getProvider();

    // 제공자에서 발급해주는 아이디
    String getProviderId();

    // 이메일
    String getEmail();

    // 사용자 이름
    String getName();

    // 이미지
    String getImageUrl();

    //
    Map<String, Object> getAttributes();
}

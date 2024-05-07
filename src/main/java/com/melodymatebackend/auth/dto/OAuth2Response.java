package com.melodymatebackend.auth.dto;

public interface OAuth2Response {

    // 제공자 (Ex. naver, kakao, google ...)
    String getProvider();

    String getProviderId();

    String getEmail();

    String getNickname();

    String getPicture();

}

package com.melodymatebackend.auth.domain;

import com.melodymatebackend.users.domain.User;
import lombok.Getter;

@Getter
public class UserDTO {

    private String nickname;
    private String email;
    private String imageUrl;
    private ProviderType providerType;
    private String providerId;

    public UserDTO(User user) {
        this.nickname = user.getNickname();
        this.email = user.getEmail();
        this.imageUrl = user.getImageUrl();
        this.providerType = user.getProviderType();
        this.providerId = user.getProviderId();
    }

}

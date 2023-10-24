package com.melodymatebackend.users.application.dto;

import com.melodymatebackend.users.domain.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsersDTO {
    private Long id;

    private String email;

    private String password;

    private String nickname;

    public User toEntity() {
        return User.builder()
                .email(email)
                .password(password)
                .nickname(nickname)
                .build();
    }
}
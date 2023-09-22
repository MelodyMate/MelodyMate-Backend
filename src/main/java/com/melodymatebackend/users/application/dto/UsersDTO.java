package com.melodymatebackend.users.application.dto;

import com.melodymatebackend.users.domain.Users;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsersDTO {
    private Long id;

    private String email;

    private String password;

    private String name;

    public UsersDTO toEntity() {
        return Users.builder()
                .email(emaial)
                .password(password)
                .name(name)
                .build();
    }
}
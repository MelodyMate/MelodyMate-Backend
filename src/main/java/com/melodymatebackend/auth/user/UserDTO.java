package com.melodymatebackend.auth.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {

    private String role;
    private String name;
    private String username;
    private String imageUrl;
}

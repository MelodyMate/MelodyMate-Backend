package com.melodymatebackend.users.domain;

import com.melodymatebackend.auth.user.OAuth2Response;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "USERS")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email", nullable = false, length = 50)
    private String email;

    @Column(name = "password", nullable = false, length = 60)
    private String password;

    @Column(name = "nickname", nullable = false, length = 30)
    private String nickname;

    private String username;

    private String picture;

    private String role;

    private String imageUrl;

    public void dataUpdate(OAuth2Response oAuth2Response, User existData) {
        this.email = oAuth2Response.getEmail();
        this.nickname = oAuth2Response.getName();
        this.username = existData.getUsername();
        this.role = existData.getRole();
        this.imageUrl = existData.getImageUrl();
    }
}
//package com.melodymatebackend.users.application.dto;
//
//import com.melodymatebackend.users.domain.User;
//import jakarta.validation.constraints.Email;
//import jakarta.validation.constraints.Pattern;
//import lombok.Getter;
//import lombok.Setter;
//import org.springframework.validation.annotation.Validated;
//
//import javax.annotation.MatchesPattern;
//
//@Getter
//@Setter
//public class UsersDTO {
//    private Long id;
//
//    @Email
//    private String email;
//
//    @Pattern(regexp = "^.{8,15}$", message = "Password must be at least 8 characters and less than 15 characters.")
//    private String password;
//
//    @Pattern(regexp = "^[A-Za-z가-힣]{1,15}$", message = "Nickname should be at least 1 character and less than 15 characters and should contain only English and Korean characters without leading or trailing spaces")
//    private String nickname;
//
//    public User toEntity() {
//        return User.builder()
//                .email(email)
//                .password(password)
//                .nickname(nickname)
//                .build();
//    }
//}
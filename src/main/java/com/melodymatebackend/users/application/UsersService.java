//package com.melodymatebackend.users.application;
//
//import com.melodymatebackend.users.application.dto.UsersDTO;
//import com.melodymatebackend.users.domain.User;
//import com.melodymatebackend.users.domain.UsersRepository;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.Collections;
//import java.util.List;
//
//@RequiredArgsConstructor
//@Transactional(readOnly = true)
//@Slf4j
//@Service
//public class UsersService {
//
//    private final UsersRepository usersRepository;
//    private final BCryptPasswordEncoder passwordEncoder;
//
//    @Transactional
//    public void join(UsersDTO usersDTO) {
//        if(usersRepository.existsByEmail(usersDTO.getEmail())) {
//            throw new IllegalArgumentException("It's a Duplicate Email");
//        }
//
//        if(usersRepository.existsByNickname(usersDTO.getNickname())) {
//            throw new IllegalArgumentException("It's a Duplicate Nickname");
//        }
//
//        String encodePassword = passwordEncoder.encode(usersDTO.getPassword());
//        usersDTO.setPassword(encodePassword);
//
//        usersRepository.save(usersDTO.toEntity());
//    }
//
//    public void login(UsersDTO usersDTO) {
//
//    }
//
//    public List<User> getUsers() {
//        return usersRepository.findAll();
//    }
//
//
//
//}

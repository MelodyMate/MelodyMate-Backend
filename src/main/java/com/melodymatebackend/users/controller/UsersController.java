//package com.melodymatebackend.users.controller;
//
//import com.melodymatebackend.common.ApiResponse;
//import com.melodymatebackend.users.application.UsersService;
//import com.melodymatebackend.users.application.dto.UsersDTO;
//import com.melodymatebackend.users.domain.User;
//import jakarta.validation.Valid;
//import java.util.List;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@Slf4j
//@RequiredArgsConstructor
//@RestController
//@Validated
//@RequestMapping("/api/v1.0")
//public class UsersController {
//
//    private final UsersService usersService;
//
//    @GetMapping("/users")
//    public ResponseEntity<List<User>> users() {
//        return ResponseEntity.ok(usersService.getUsers());
//    }
//
//    @PostMapping("/signup")
//    public ResponseEntity<ApiResponse> signup(@Valid @RequestBody UsersDTO usersDTO) {
//        usersService.join(usersDTO);
//        return new ResponseEntity<>(
//            new ApiResponse(HttpStatus.CREATED.value(), HttpStatus.CREATED.name(), usersDTO),
//            HttpStatus.CREATED);
//    }
//
//    @PostMapping("/signin")
//    public ResponseEntity<ApiResponse> signin(@Valid @RequestBody UsersDTO usersDTO) {
//        usersService.login(usersDTO);
//        return new ResponseEntity<>(
//            new ApiResponse(HttpStatus.OK.value(), HttpStatus.OK.name(), usersDTO), HttpStatus.OK);
//    }
//}
//

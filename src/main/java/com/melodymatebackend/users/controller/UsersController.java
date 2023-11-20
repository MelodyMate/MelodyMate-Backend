package com.melodymatebackend.users.controller;

import com.melodymatebackend.common.ApiResponse;
import com.melodymatebackend.common.exception.ApiException;
import com.melodymatebackend.users.application.UsersService;
import com.melodymatebackend.users.application.dto.UsersDTO;
import com.melodymatebackend.users.domain.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1.0")
@Validated
public class UsersController {

    private final UsersService usersService;

    @PostMapping("/signup")
    public ResponseEntity<ApiResponse> signup(@Valid @RequestBody UsersDTO usersDTO, BindingResult bindingResult) {

        usersService.join(usersDTO);

        return new ResponseEntity<>(new ApiResponse(HttpStatus.CREATED.value(), HttpStatus.CREATED.name(), usersDTO), HttpStatus.CREATED);
    }
}

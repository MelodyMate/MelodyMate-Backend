package com.melodymatebackend.users.controller;

import com.melodymatebackend.users.application.UsersService;
import com.melodymatebackend.users.application.dto.UsersDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1.0")
public class UsersController {

    private final UsersService usersService;

    @PostMapping("/users")
    public ResponseEntity<Map<String, Object>> join(UsersDTO usersDTO) {


        return ResponseEntity.ok();
    }

    @GetMapping("/admin")
    public void musicSave() throws InterruptedException {
        crawlingService.crawlingMain();
    }

}

package com.quoter.quoter.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quoter.quoter.service.UserService;

@RestController
@RequestMapping("/api")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("register")
    public ResponseEntity<?> register() {
        // logic here
        return ResponseEntity.ok("User registered succesfully");
    }

    @PostMapping("login")
    public ResponseEntity<?> login() {
        // logic here
        return ResponseEntity.ok("User login succesfully");
    }

    @PostMapping("logout")
    public ResponseEntity<?> logout() {
        // logic here
        return ResponseEntity.ok("User logout succesfully");
    }
}

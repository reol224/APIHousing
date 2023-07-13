package com.conestoga.APIHousing.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController<LoginRequest> {

    // test getmapping
    // @GetMapping("/test")
    // public ResponseEntity<String> test() {
    // return ResponseEntity.ok("Test worked!");
    // }

    // @GetMapping("/test2")
    // public ResponseEntity<String> test2() {
    // return ResponseEntity.ok("Test2 worked!");
    // }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser() {
        // Implement your logic to register the user
        return new ResponseEntity<>("User registered successfully", HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody LoginRequest request) {
        // Implement your logic to authenticate and login the user
        return new ResponseEntity<>("User logged in successfully", HttpStatus.OK);
    }
}
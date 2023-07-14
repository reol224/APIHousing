package com.conestoga.APIHousing.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController<LoginRequest> {

    //Quick test
    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("Test worked!");
    }

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
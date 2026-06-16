package com.sweetshop.controller;

import com.sweetshop.dto.AuthRequest;
import com.sweetshop.dto.AuthResponse;
import com.sweetshop.model.Role;
import com.sweetshop.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    // REGISTER USER
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody AuthRequest request) {
        authService.register(request, Role.USER);
        return ResponseEntity.ok("User registered successfully");
    }

    // LOGIN
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }
}

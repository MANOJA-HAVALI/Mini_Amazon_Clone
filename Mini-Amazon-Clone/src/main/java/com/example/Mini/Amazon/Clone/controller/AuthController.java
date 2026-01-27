package com.example.Mini.Amazon.Clone.controller;

import com.example.Mini.Amazon.Clone.dto.requests.UserLoginRequestDTO;
import com.example.Mini.Amazon.Clone.dto.requests.UserRequestDTO;
import com.example.Mini.Amazon.Clone.dto.requests.responses.LoginResponseDTO;
import com.example.Mini.Amazon.Clone.services.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    // ---------------- REGISTER API ----------------
    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody UserRequestDTO request) {
        String message = authService.register(request);
        return ResponseEntity.ok(message);
    }

    // ---------------- LOGIN API ----------------
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(
            @Valid @RequestBody UserLoginRequestDTO request) {

        LoginResponseDTO response = authService.login(request);
        return ResponseEntity.ok(response);
    }
}

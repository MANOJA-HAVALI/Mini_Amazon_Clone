package com.example.Mini.Amazon.Clone.controller;

import com.example.Mini.Amazon.Clone.config.JwtUtil;
import com.example.Mini.Amazon.Clone.dto.requests.UserLoginRequestDTO;
import com.example.Mini.Amazon.Clone.dto.requests.UserRequestDTO;
import com.example.Mini.Amazon.Clone.entity.User;
import com.example.Mini.Amazon.Clone.exception.ResourceNotFoundException;
import com.example.Mini.Amazon.Clone.repository.UserRepository;
import com.example.Mini.Amazon.Clone.services.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;

    // ---------------- REGISTER API ----------------
    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody UserRequestDTO request) {
        String message = authService.register(request);
        return ResponseEntity.ok(message);
    }

    // ---------------- LOGIN API ----------------
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserLoginRequestDTO request) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        // ACCESS TOKEN (15 min)
        String accessToken = jwtUtil.generateToken(
                user.getEmail(),
                user.getRole().name(),
                true
        );

        // REFRESH TOKEN (30 days)
        String refreshToken = jwtUtil.generateToken(
                user.getEmail(),
                user.getRole().name(),
                false
        );

        return ResponseEntity.ok(
                Map.of(
                        "email", user.getEmail(),
                        "role", user.getRole(),
                        "accessToken", accessToken,
                        "refreshToken", refreshToken
                )
        );
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refreshToken(@RequestParam String refreshToken) {

        if (!jwtUtil.validateToken(refreshToken)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Invalid refresh token");
        }

        String email = jwtUtil.extractUsername(refreshToken);

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        String newAccessToken =
                jwtUtil.generateToken(user.getEmail(), user.getRole().name(), true);

        return ResponseEntity.ok(
                Map.of("accessToken", newAccessToken)
        );
    }
}

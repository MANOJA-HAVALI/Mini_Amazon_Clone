package com.example.Mini.Amazon.Clone.services.Impl;

import com.example.Mini.Amazon.Clone.config.JwtUtil;
import com.example.Mini.Amazon.Clone.dto.requests.UserLoginRequestDTO;
import com.example.Mini.Amazon.Clone.dto.requests.UserRequestDTO;
import com.example.Mini.Amazon.Clone.dto.requests.responses.LoginResponseDTO;
import com.example.Mini.Amazon.Clone.entity.User;
import com.example.Mini.Amazon.Clone.enums.Role;
import com.example.Mini.Amazon.Clone.repository.UserRepository;
import com.example.Mini.Amazon.Clone.services.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public AuthServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder,
            AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public String register(UserRequestDTO request) {
        // Check email already exists
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        // Map DTO → Entity
        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        // FORCE role internally
        user.setRole(Role.USER);

        // Save
        userRepository.save(user);

        return "User registered successfully";
    }

    @Override
    public LoginResponseDTO login(UserLoginRequestDTO request) {
        // Authenticate credentials
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()));
        // Fetch user details
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Generate JWT
        String token = jwtUtil.generateToken(
                user.getEmail(),
                user.getRole().name());

        // Prepare response
        LoginResponseDTO response = new LoginResponseDTO();
        response.setToken(token);
        response.setEmail(user.getEmail());
        response.setRole(user.getRole());

        return response;
    }
}

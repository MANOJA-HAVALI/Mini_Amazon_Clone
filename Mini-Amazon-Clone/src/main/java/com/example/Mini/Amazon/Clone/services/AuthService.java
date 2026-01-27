package com.example.Mini.Amazon.Clone.services;

import com.example.Mini.Amazon.Clone.dto.requests.UserLoginRequestDTO;
import com.example.Mini.Amazon.Clone.dto.requests.UserRequestDTO;
import com.example.Mini.Amazon.Clone.dto.requests.responses.LoginResponseDTO;

public interface AuthService {
    String register(UserRequestDTO request);
    LoginResponseDTO login(UserLoginRequestDTO request);
}

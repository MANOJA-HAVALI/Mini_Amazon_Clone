package com.example.Mini.Amazon.Clone.services;

import com.example.Mini.Amazon.Clone.dto.requests.UserRequestDTO;
import com.example.Mini.Amazon.Clone.dto.requests.responses.UserResponseDTO;

import java.util.List;

public interface UserService {
    UserResponseDTO createUser(UserRequestDTO userRequestDTO);

    UserResponseDTO updateUser(Long id, UserRequestDTO userRequestDTO);

    void deleteUser(Long id);

    UserResponseDTO getUserById(Long id);

    List<UserResponseDTO> getAllUsers();
}

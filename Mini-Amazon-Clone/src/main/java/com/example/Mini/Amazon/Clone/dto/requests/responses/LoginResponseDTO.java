package com.example.Mini.Amazon.Clone.dto.requests.responses;

import com.example.Mini.Amazon.Clone.enums.Role;
import lombok.Data;

@Data
public class LoginResponseDTO {

    private String token;
    private String email;
    private Role role;
}

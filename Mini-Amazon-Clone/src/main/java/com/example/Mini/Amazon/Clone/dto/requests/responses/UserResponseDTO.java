package com.example.Mini.Amazon.Clone.dto.requests.responses;

import com.example.Mini.Amazon.Clone.enums.Role;
import lombok.Data;

import java.util.List;

@Data
public class UserResponseDTO {

    private Long id;

    private String name;

    private String email;

    private Role role;

    private Long cartId;

    private List<Long> orderIds;
}

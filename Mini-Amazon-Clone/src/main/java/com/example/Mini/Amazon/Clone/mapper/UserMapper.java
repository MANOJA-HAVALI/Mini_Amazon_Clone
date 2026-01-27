package com.example.Mini.Amazon.Clone.mapper;

import com.example.Mini.Amazon.Clone.dto.requests.responses.UserResponseDTO;
import com.example.Mini.Amazon.Clone.entity.Order;
import com.example.Mini.Amazon.Clone.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserResponseDTO mapToResponse(User user) {
        UserResponseDTO dto = new UserResponseDTO();

        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setRole(user.getRole());

        if (user.getCart() != null) {
            dto.setCartId(user.getCart().getId());
        }

        if (user.getOrders() != null) {
            dto.setOrderIds(
                    user.getOrders()
                            .stream()
                            .map(Order::getId)
                            .toList());
        }

        return dto;
    }
}

package com.example.Mini.Amazon.Clone.mapper;

import com.example.Mini.Amazon.Clone.dto.requests.responses.CartItemResponseDTO;
import com.example.Mini.Amazon.Clone.dto.requests.responses.CartResponseDTO;
import com.example.Mini.Amazon.Clone.entity.Cart;

import java.util.stream.Collectors;

public class CartMapper {

    public static CartResponseDTO toDTO(Cart cart) {

        CartResponseDTO dto = new CartResponseDTO();
        dto.setCartId(cart.getId());

        dto.setItems(
                cart.getCartItems().stream().map(item -> {
                    CartItemResponseDTO itemDTO = new CartItemResponseDTO();
                    itemDTO.setProductId(item.getProduct().getId());
                    itemDTO.setProductName(item.getProduct().getName());
                    itemDTO.setQuantity(item.getQuantity());
                    itemDTO.setPrice(item.getProduct().getPrice());
                    return itemDTO;
                }).collect(Collectors.toList())
        );

        double total = cart.getCartItems().stream()
                .mapToDouble(i -> i.getQuantity() * i.getProduct().getPrice())
                .sum();

        dto.setTotalAmount(total);

        return dto;

    }
}

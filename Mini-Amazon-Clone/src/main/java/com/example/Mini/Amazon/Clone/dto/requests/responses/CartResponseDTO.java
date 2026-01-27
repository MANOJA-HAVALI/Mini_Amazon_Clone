package com.example.Mini.Amazon.Clone.dto.requests.responses;

import lombok.Data;

import java.util.List;

@Data
public class CartResponseDTO {

    private Long cartId;
    private List<CartItemResponseDTO> items;
    private Double totalAmount;
}

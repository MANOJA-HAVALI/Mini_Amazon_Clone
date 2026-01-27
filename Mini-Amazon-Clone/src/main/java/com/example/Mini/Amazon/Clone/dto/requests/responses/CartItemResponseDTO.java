package com.example.Mini.Amazon.Clone.dto.requests.responses;

import lombok.Data;

@Data
public class CartItemResponseDTO {
    private Long productId;
    private String productName;
    private Integer quantity;
    private Double price;
}

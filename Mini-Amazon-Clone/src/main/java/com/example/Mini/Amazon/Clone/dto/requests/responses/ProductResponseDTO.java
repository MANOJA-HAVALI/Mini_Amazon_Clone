package com.example.Mini.Amazon.Clone.dto.requests.responses;

import lombok.Data;

@Data
public class ProductResponseDTO {
    private Long id;
    private String name;
    private String description;
    private double price;
    private int stock;
    private String category;
    private boolean enabled;
}

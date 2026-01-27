package com.example.Mini.Amazon.Clone.services;

import com.example.Mini.Amazon.Clone.dto.requests.ProductRequestDTO;
import com.example.Mini.Amazon.Clone.dto.requests.responses.ProductResponseDTO;
import com.example.Mini.Amazon.Clone.entity.Product;

import java.util.List;

public interface ProductService {

    ProductResponseDTO addProduct(ProductRequestDTO request);

    ProductResponseDTO updateProduct(Long productId, ProductRequestDTO request);

    ProductResponseDTO getProductById(Long productId);

    List<Product> getAllProducts();

    void deleteProduct(Long productId);        // soft delete

    void enableProduct(Long productId);

    void disableProduct(Long productId);
}

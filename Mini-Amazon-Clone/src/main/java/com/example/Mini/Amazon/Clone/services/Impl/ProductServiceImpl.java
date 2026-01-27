package com.example.Mini.Amazon.Clone.services.Impl;

import com.example.Mini.Amazon.Clone.dto.requests.ProductRequestDTO;
import com.example.Mini.Amazon.Clone.dto.requests.responses.ProductResponseDTO;
import com.example.Mini.Amazon.Clone.entity.Product;
import com.example.Mini.Amazon.Clone.mapper.ProductMapper;
import com.example.Mini.Amazon.Clone.repository.ProductRepository;
import com.example.Mini.Amazon.Clone.services.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.Mini.Amazon.Clone.mapper.ProductMapper.toDTO;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ProductResponseDTO addProduct(ProductRequestDTO request) {
        Product product = ProductMapper.toEntity(request);

        Product savedProduct = productRepository.save(product);
        return toDTO(savedProduct);
    }

    @Override
    public ProductResponseDTO updateProduct(Long productId, ProductRequestDTO request) {

        Product product = productRepository.findByIdAndDeletedFalse(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setStock(request.getStock());
        product.setCategory(request.getCategory());

        return toDTO(productRepository.save(product));
    }

    @Override
    public ProductResponseDTO getProductById(Long productId) {
        Product product = productRepository.findByIdAndDeletedFalse(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        return toDTO(product);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public void deleteProduct(Long productId) {

        Product product = productRepository.findByIdAndDeletedFalse(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        product.setDeleted(true);   //  soft delete
        productRepository.save(product);
    }

    @Override
    public void enableProduct(Long productId) {
        Product product = productRepository.findByIdAndDeletedFalse(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        product.setEnabled(true);
        productRepository.save(product);
    }

    @Override
    public void disableProduct(Long productId) {
        Product product = productRepository.findByIdAndDeletedFalse(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        product.setEnabled(false);
        productRepository.save(product);
    }
}

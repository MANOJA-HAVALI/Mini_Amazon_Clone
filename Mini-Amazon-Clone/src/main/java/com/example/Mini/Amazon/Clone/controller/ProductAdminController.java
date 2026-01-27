package com.example.Mini.Amazon.Clone.controller;

import com.example.Mini.Amazon.Clone.dto.requests.ProductRequestDTO;
import com.example.Mini.Amazon.Clone.dto.requests.responses.ProductResponseDTO;
import com.example.Mini.Amazon.Clone.entity.Product;
import com.example.Mini.Amazon.Clone.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/products")
public class ProductAdminController {

    private final ProductService productService;

    public ProductAdminController(ProductService productService) {
        this.productService = productService;
    }

    //  Add Product
    @PostMapping("/addProduct")
    public ResponseEntity<ProductResponseDTO> addProduct(
            @Valid @RequestBody ProductRequestDTO request) {

        return ResponseEntity.ok(productService.addProduct(request));
    }

    //  Update Product
    @PutMapping("/updateProduct/{id}")
    public ResponseEntity<ProductResponseDTO> updateProduct(
            @PathVariable Long id,
            @Valid @RequestBody ProductRequestDTO request) {

        return ResponseEntity.ok(productService.updateProduct(id, request));
    }
    //  Get Product by ID
    @GetMapping("/getProductById/{id}")
    public ResponseEntity<ProductResponseDTO> getProductById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }
    // get all products
    @GetMapping("/getAllProducts")
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    //  Soft Delete
    @DeleteMapping("/deleteProduct/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {

        productService.deleteProduct(id);
        return ResponseEntity.ok("Product deleted successfully");
    }

    //  Disable Product
    @PutMapping("/{id}/disableProduct")
    public ResponseEntity<String> disableProduct(@PathVariable Long id) {

        productService.disableProduct(id);
        return ResponseEntity.ok("Product disabled");
    }

    //  Enable Product
    @PutMapping("/{id}/enableProduct")
    public ResponseEntity<String> enableProduct(@PathVariable Long id) {

        productService.enableProduct(id);
        return ResponseEntity.ok("Product enabled");
    }
}


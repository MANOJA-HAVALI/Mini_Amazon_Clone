package com.example.Mini.Amazon.Clone.repository;

import com.example.Mini.Amazon.Clone.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Page<Product> findByNameContainingIgnoreCase(String keyword, Pageable pageable);

    Page<Product> findByCategory(String category, Pageable pageable);

    Optional<Product> findByIdAndDeletedFalse(Long id);

    List<Product> findByDeletedFalseAndEnabledTrue();
}

package com.example.Mini.Amazon.Clone.repository;

import com.example.Mini.Amazon.Clone.entity.Cart;
import com.example.Mini.Amazon.Clone.entity.CartItem;
import com.example.Mini.Amazon.Clone.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    Optional<CartItem> findByCartIdAndProductId(Long cartId, Long productId);

    List<CartItem> findByCartId(Long cartId);

    void deleteByCartId(Long cartId);

    Optional<CartItem> findByCartAndProduct(Cart cart, Product product);
}

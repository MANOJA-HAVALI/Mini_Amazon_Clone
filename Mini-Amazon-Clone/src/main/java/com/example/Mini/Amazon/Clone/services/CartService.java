package com.example.Mini.Amazon.Clone.services;

import com.example.Mini.Amazon.Clone.entity.Cart;

public interface CartService {

    void addToCart(Long productId, int quantity, String userEmail);

    Cart getMyCart(long userId);

    void removeItem(Long cartItemId, String userEmail);

    void clearCart(String userEmail);
}

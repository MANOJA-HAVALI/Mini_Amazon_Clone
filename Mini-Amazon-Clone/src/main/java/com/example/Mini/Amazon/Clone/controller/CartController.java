package com.example.Mini.Amazon.Clone.controller;

import com.example.Mini.Amazon.Clone.entity.Cart;
import com.example.Mini.Amazon.Clone.services.CartService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    // Add product to cart
    @PostMapping("/add")
    public ResponseEntity<String> addToCart(
            @RequestParam Long productId,
            @RequestParam int quantity,
            @RequestParam String userEmail) {

        cartService.addToCart(productId, quantity, userEmail);
        return ResponseEntity.ok("Product added to cart");
    }

    // Get my cart (by userId)
    @GetMapping("/myCart")
    public ResponseEntity<Cart> getMyCart(
            @RequestParam Long userId) {

        Cart cart = cartService.getMyCart(userId);
        return ResponseEntity.ok(cart);
    }

    //  Remove item from cart
    @DeleteMapping("/removeItem/{cartItemId}")
    public ResponseEntity<String> removeItem(
            @PathVariable Long cartItemId,
            @RequestParam String userEmail) {

        cartService.removeItem(cartItemId, userEmail);
        return ResponseEntity.ok("Item removed from cart");
    }

    //  Clear cart
    @DeleteMapping("/clear")
    public ResponseEntity<String> clearCart(
            @RequestParam String userEmail) {

        cartService.clearCart(userEmail);
        return ResponseEntity.ok("Cart cleared");
    }
//    //  Add product to cart
//    @PostMapping("/add")
//    public ResponseEntity<String> addToCart(@Valid @RequestParam Long productId, @RequestParam int quantity, Authentication authentication ) {
//        String userEmail = authentication.getName();
//        cartService.addToCart(productId, quantity, userEmail);
//        return ResponseEntity.ok("Product added to cart");
//    }
//
//    // View my cart
//    @GetMapping("/myCart")
//    public ResponseEntity<Cart> getMyCart(@Valid Authentication authentication) {
//        String userEmail = authentication.getName();
//        return ResponseEntity.ok(cartService.getMyCart(Long.parseLong(userEmail)));
//    }
//
//    // Remove item
//    @DeleteMapping("/removeItem/item/{id}")
//    public ResponseEntity<String> removeItem(@PathVariable Long id,
//                                             Authentication authentication) {
//
//        cartService.removeItem(id, authentication.getName());
//        return ResponseEntity.ok("Item removed from cart");
//    }
//
//    //  Clear cart
//    @DeleteMapping("/clear")
//    public ResponseEntity<String> clearCart(Authentication authentication) {
//
//        cartService.clearCart(authentication.getName());
//        return ResponseEntity.ok("Cart cleared");
//    }
}

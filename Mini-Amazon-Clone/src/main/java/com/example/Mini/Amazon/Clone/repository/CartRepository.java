package com.example.Mini.Amazon.Clone.repository;

import com.example.Mini.Amazon.Clone.entity.Cart;
import com.example.Mini.Amazon.Clone.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findByUserId(Long userId);
    Optional<Cart> findByUser(User user);

}

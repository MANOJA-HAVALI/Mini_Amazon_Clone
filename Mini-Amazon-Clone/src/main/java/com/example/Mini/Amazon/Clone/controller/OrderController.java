package com.example.Mini.Amazon.Clone.controller;

import com.example.Mini.Amazon.Clone.entity.Order;
import com.example.Mini.Amazon.Clone.services.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    // ================= PLACE ORDER =================
    @PostMapping("/place")
    public ResponseEntity<Order> placeOrder(@RequestParam String email) {

        Order order = orderService.placeOrder(email);
        return ResponseEntity.ok(order);
    }

    // ================= GET MY ORDERS =================
    @GetMapping("/my")
    public ResponseEntity<List<Order>> getMyOrders(@RequestParam String email) {

        return ResponseEntity.ok(orderService.getMyOrders(email));
    }

    // ================= GET ORDER BY ID =================
    @GetMapping("/{orderId}")
    public ResponseEntity<Order> getOrderById(
            @PathVariable Long orderId,
            @RequestParam String email) {

        return ResponseEntity.ok(orderService.getOrderById(orderId, email));
    }

    // ================= CANCEL ORDER =================
    @PutMapping("/cancel/{orderId}")
    public ResponseEntity<String> cancelOrder(
            @PathVariable Long orderId,
            @RequestParam String email) {

        orderService.cancelOrder(orderId, email);
        return ResponseEntity.ok("Order cancelled successfully");
    }
}


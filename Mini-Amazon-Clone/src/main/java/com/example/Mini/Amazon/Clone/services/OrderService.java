package com.example.Mini.Amazon.Clone.services;

import com.example.Mini.Amazon.Clone.entity.Order;

import java.util.List;

public interface OrderService {

    Order placeOrder(String userEmail);

    List<Order> getMyOrders(String userEmail);

    Order getOrderById(Long orderId, String userEmail);

    void cancelOrder(Long orderId, String userEmail);
}

package com.snoozingturtles.auctioz.services;

import com.snoozingturtles.auctioz.dto.OrderDto;

public interface OrderService {
    OrderDto createOrder(OrderDto orderDto);
    OrderDto getOrderById(String orderId);
    void deleteOrder(String orderId);
}

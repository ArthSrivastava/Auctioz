package com.snoozingturtles.auctioz.services.impl;

import com.snoozingturtles.auctioz.dto.OrderDto;
import com.snoozingturtles.auctioz.models.Order;
import com.snoozingturtles.auctioz.repositories.OrderRepo;
import com.snoozingturtles.auctioz.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepo orderRepo;
    private final ModelMapper modelMapper;

    @Override
    public OrderDto createOrder(OrderDto orderDto) {
        Order savedOrder = orderRepo.save(modelMapper.map(orderDto, Order.class));
        return modelMapper.map(savedOrder, OrderDto.class);
    }

    public OrderDto getOrderById(String orderId) {
        return modelMapper.map(orderRepo.findById(orderId), OrderDto.class);
    }
    @Override
    public void deleteOrder(String orderId) {
        orderRepo.delete(modelMapper.map(getOrderById(orderId), Order.class));
    }
}

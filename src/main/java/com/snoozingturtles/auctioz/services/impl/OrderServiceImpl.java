package com.snoozingturtles.auctioz.services.impl;

import com.snoozingturtles.auctioz.dto.OrderDto;
import com.snoozingturtles.auctioz.dto.ProductDto;
import com.snoozingturtles.auctioz.models.Order;
import com.snoozingturtles.auctioz.repositories.OrderRepo;
import com.snoozingturtles.auctioz.services.OrderService;
import com.snoozingturtles.auctioz.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepo orderRepo;
    private final ModelMapper modelMapper;
    private final ProductService productService;

    @Override
    public OrderDto createOrder(OrderDto orderDto) {
        ProductDto productById = productService.getProductById(orderDto.getProductId());
        productById.setSoldOut(true);
        productService.updateProduct(productById.getId(), productById.getSellerId(), productById);
        orderDto.setTimestamp(LocalDateTime.now());
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

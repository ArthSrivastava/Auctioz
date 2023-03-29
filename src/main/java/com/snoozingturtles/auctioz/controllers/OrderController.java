package com.snoozingturtles.auctioz.controllers;

import com.snoozingturtles.auctioz.dto.OrderDto;
import com.snoozingturtles.auctioz.payloads.ApiResponse;
import com.snoozingturtles.auctioz.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<ApiResponse> createOrder(@RequestBody OrderDto orderDto) {
        OrderDto order = orderService.createOrder(orderDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{orderId}")
                .buildAndExpand(order.getId()).toUri();
        return ResponseEntity.created(uri).body(
                ApiResponse.builder()
                        .message("Order placed successfully!")
                        .success(true)
                        .build()
        );
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderDto> getOrderById(@PathVariable String orderId) {
        return ResponseEntity.ok(orderService.getOrderById(orderId));
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<ApiResponse> deleteOrder(@PathVariable String orderId) {
        orderService.deleteOrder(orderId);
        return ResponseEntity.ok(
                ApiResponse.builder()
                        .message("Order deleted successfully")
                        .success(true)
                        .build()
        );
    }
}

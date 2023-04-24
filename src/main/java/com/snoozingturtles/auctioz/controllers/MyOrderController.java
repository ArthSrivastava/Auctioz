package com.snoozingturtles.auctioz.controllers;

import com.razorpay.RazorpayException;
import com.snoozingturtles.auctioz.dto.MyOrderDto;
import com.snoozingturtles.auctioz.payloads.ApiResponse;
import com.snoozingturtles.auctioz.services.MyOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class MyOrderController {
    private final MyOrderService myOrderService;

    @PostMapping
    @PreAuthorize(value = "@userSecurity.hasUserId(authentication, #myOrderDto.userId)")
    public ResponseEntity<MyOrderDto> createOrder(@RequestBody MyOrderDto myOrderDto) throws RazorpayException {
        MyOrderDto order = myOrderService.createOrder(myOrderDto);
        return ResponseEntity.ok(order);
    }

    @PutMapping
    public ResponseEntity<ApiResponse> updateOrder(@RequestBody MyOrderDto myOrderDto) {
        myOrderService.updatePaymentSuccess(myOrderDto);
        return ResponseEntity.ok(
                ApiResponse.builder()
                        .message("Order placed successfully!")
                        .success(true)
                        .build()
        );
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<MyOrderDto> getOrderById(@PathVariable String orderId) {
        return ResponseEntity.ok(myOrderService.getOrderById(orderId));
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<ApiResponse> deleteOrder(@PathVariable String orderId) {
        myOrderService.deleteOrder(orderId);
        return ResponseEntity.ok(
                ApiResponse.builder()
                        .message("Order deleted successfully")
                        .success(true)
                        .build()
        );
    }
}

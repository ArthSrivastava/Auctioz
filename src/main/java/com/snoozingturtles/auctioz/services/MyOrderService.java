package com.snoozingturtles.auctioz.services;

import com.razorpay.RazorpayException;
import com.snoozingturtles.auctioz.dto.MyOrderDto;

public interface MyOrderService {
    MyOrderDto createOrder(MyOrderDto myOrderDto) throws RazorpayException;
    void updatePaymentSuccess(MyOrderDto myOrderDto);
    MyOrderDto getOrderById(String orderId);
    void deleteOrder(String orderId);
}

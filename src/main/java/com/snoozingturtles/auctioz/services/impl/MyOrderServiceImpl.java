package com.snoozingturtles.auctioz.services.impl;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import com.snoozingturtles.auctioz.dto.MyOrderDto;
import com.snoozingturtles.auctioz.exceptions.EntityNotFoundException;
import com.snoozingturtles.auctioz.models.MyOrder;
import com.snoozingturtles.auctioz.repositories.MyOrderRepo;
import com.snoozingturtles.auctioz.services.MyOrderService;
import com.snoozingturtles.auctioz.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
@RequiredArgsConstructor
public class MyOrderServiceImpl implements MyOrderService {
    private final MyOrderRepo myOrderRepo;
    private final ModelMapper modelMapper;
    private final ProductService productService;

    @Value("${rzp.key.id}")
    private String keyId;

    @Value("${rzp.key.secret}")
    private String keySecret;
    @Override
    public MyOrderDto createOrder(MyOrderDto myOrderDto) throws RazorpayException {
        RazorpayClient razorpayClient = new RazorpayClient(keyId, keySecret);
        JSONObject orderRequest = new JSONObject();

        long amount = myOrderDto.getAmount();
        //Convert amount to paise (amount * 100)
        orderRequest.put("amount", amount * 100);
        orderRequest.put("currency", "INR");
        Order myOrder = razorpayClient.orders.create(orderRequest);

        //save order details to DB as per requirement
        myOrderDto.setTimestamp(LocalDateTime.now());
        myOrderDto.setAmount(myOrder.get("amount"));
        myOrderDto.setRazorpayOrderId(myOrder.get("id"));
        myOrderDto.setStatus(myOrder.get("status"));

        MyOrder savedMyOrder = myOrderRepo.save(modelMapper.map(myOrderDto, MyOrder.class));
        return modelMapper.map(savedMyOrder, MyOrderDto.class);
    }

    @Override
    public void updatePaymentSuccess(MyOrderDto myOrderDto) {
        MyOrder myOrder = myOrderRepo.findByRazorpayOrderId(myOrderDto.getRazorpayOrderId())
                .orElseThrow(() -> new EntityNotFoundException("No such order found!"));
        myOrder.setPaymentId(myOrderDto.getPaymentId());
        myOrder.setStatus(myOrderDto.getStatus());
        myOrderRepo.save(myOrder);
    }
    public MyOrderDto getOrderById(String orderId) {
        return modelMapper.map(myOrderRepo.findById(orderId), MyOrderDto.class);
    }
    @Override
    public void deleteOrder(String orderId) {
        myOrderRepo.delete(modelMapper.map(getOrderById(orderId), MyOrder.class));
    }
}

package com.snoozingturtles.auctioz.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MyOrder {
    @Id
    private String id;
    private String userId;
    private String productId;
    private String razorpayOrderId;
    private long amount;
    private String status;
    private String paymentId;
    private LocalDateTime timestamp;
}

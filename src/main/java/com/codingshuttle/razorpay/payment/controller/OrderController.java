package com.codingshuttle.razorpay.payment.controller;

import com.codingshuttle.razorpay.payment.dto.request.CreateOrderRequest;
import com.codingshuttle.razorpay.payment.dto.response.OrderResponse;
import com.codingshuttle.razorpay.payment.repository.OrderRepository;
import com.codingshuttle.razorpay.payment.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/v1/orders")
@RequiredArgsConstructor

public class OrderController {

    private final  OrderService orderService;

    UUID merchantId=UUID.fromString("5cb52376-00d5-49e1-8d0c-d3c00c381e18");

    @PostMapping
    public ResponseEntity<OrderResponse> createOrder(@RequestBody @Valid CreateOrderRequest request){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(orderService.createOrder(merchantId, request));
    }
}

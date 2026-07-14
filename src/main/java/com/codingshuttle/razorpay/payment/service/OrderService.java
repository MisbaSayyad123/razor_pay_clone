package com.codingshuttle.razorpay.payment.service;

import com.codingshuttle.razorpay.payment.dto.request.CreateOrderRequest;
import com.codingshuttle.razorpay.payment.dto.response.OrderResponse;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface OrderService {
    OrderResponse createOrder(UUID merchantId, CreateOrderRequest request);
}

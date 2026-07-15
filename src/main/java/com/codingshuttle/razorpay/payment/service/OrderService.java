package com.codingshuttle.razorpay.payment.service;

import com.codingshuttle.razorpay.payment.dto.request.CreateOrderRequest;
import com.codingshuttle.razorpay.payment.dto.response.OrderResponse;
import com.codingshuttle.razorpay.payment.dto.response.PaymentResponse;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface OrderService {
    OrderResponse createOrder(UUID merchantId, CreateOrderRequest request);
    OrderResponse getById(UUID orderId, UUID merchantId);
    OrderResponse cancelOrder(UUID orderId, UUID merchantId);
    List<PaymentResponse> paymentList(UUID orderId, UUID merchantId);
}

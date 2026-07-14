package com.codingshuttle.razorpay.payment.service.impl;

import com.codingshuttle.razorpay.common.eums.OrderStatus;
import com.codingshuttle.razorpay.common.exception.DuplicateResourceException;
import com.codingshuttle.razorpay.payment.dto.request.CreateOrderRequest;
import com.codingshuttle.razorpay.payment.dto.response.OrderResponse;
import com.codingshuttle.razorpay.payment.entity.OrderRecord;
import com.codingshuttle.razorpay.payment.repository.OrderRepository;
import com.codingshuttle.razorpay.payment.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j

public class OrderServiceImpl implements OrderService {

    private  final OrderRepository orderRepository;

    @Value("${payment.order.default-order-expiry-minutes:30}")
    private  int defaultOrderExpiryMinute;

    @Override
    public OrderResponse createOrder(UUID merchantId, CreateOrderRequest request) {
        if(request.receipt()!=null && orderRepository.existsByMerchantIdAndReceipt(merchantId, request.receipt()))
        {
            throw new DuplicateResourceException("Order_RECEIPT_DUPLICATE", "Order with receipt already exist "+request.receipt());
        }

        OrderRecord orderRecord= OrderRecord.builder()
                .receipt(request.receipt())
                .amount(request.amount())
                .notes(request.notes())
                .merchantId(merchantId)
                .orderStatus(OrderStatus.CREATED)
                .expiresAt(request.expiredAt()!=null?request.expiredAt():
                        LocalDateTime.now().plusMinutes(defaultOrderExpiryMinute))
                .build();

        orderRecord=orderRepository.save(orderRecord);

        return new OrderResponse(orderRecord.getId(),
                orderRecord.getMerchantId(),
                orderRecord.getReceipt(),
                orderRecord.getAmount(),
                orderRecord.getOrderStatus(),
                orderRecord.getAttempts(),
                orderRecord.getNotes(),
                orderRecord.getExpiresAt(),
                null);

    }
}

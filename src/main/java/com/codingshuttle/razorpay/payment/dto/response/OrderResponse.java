package com.codingshuttle.razorpay.payment.dto.response;

import com.codingshuttle.razorpay.common.entity.Money;
import com.codingshuttle.razorpay.common.eums.OrderStatus;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

public record OrderResponse(

        UUID id,
        UUID merchantId,
        String receipt,
        Money amount,
        OrderStatus status,
        Integer Attempts,
        Map<String,Object> Notes,
        LocalDateTime expiresAt,
        LocalDateTime createdAt
) {
}

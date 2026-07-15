package com.codingshuttle.razorpay.payment.dto.response;

import com.codingshuttle.razorpay.common.entity.Money;
import com.codingshuttle.razorpay.common.eums.PaymentMethod;
import com.codingshuttle.razorpay.common.eums.PaymentStatus;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

public record PaymentResponse(
        UUID id,
        UUID merchantId,
        UUID orderId,
        Money amount,
        PaymentStatus status,
        PaymentMethod method,
        Map<String, Objects> methodDetails,
        String errorCode,
        String errorDescription,
        LocalDateTime createdAt,
        LocalDateTime capturedAt) {
}

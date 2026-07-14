package com.codingshuttle.razorpay.merchant.dto.response;

import com.codingshuttle.razorpay.common.eums.Environment;

import java.time.LocalDateTime;
import java.util.UUID;

public record ApiKeyResponse(

        UUID id,
        String keyId,
        Environment environment,
        boolean enabled,
        LocalDateTime lastUsedAt,
        LocalDateTime createdAt
) {
}

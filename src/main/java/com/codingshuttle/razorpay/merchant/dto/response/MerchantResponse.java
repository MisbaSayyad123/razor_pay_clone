package com.codingshuttle.razorpay.merchant.dto.response;

import com.codingshuttle.razorpay.common.eums.BusinessType;
import com.codingshuttle.razorpay.common.eums.MerchantStatus;
import lombok.Getter;

import java.util.UUID;

public record MerchantResponse(
        UUID id,

        String name,

        String email,

        String businessName,

        BusinessType businessType,

        MerchantStatus merchantStatus

) {
}

package com.codingshuttle.razorpay.merchant.dto.request;

import com.codingshuttle.razorpay.common.eums.Environment;

import java.util.UUID;

public record CreateApiKeyRequest (
        Environment environment
){
}

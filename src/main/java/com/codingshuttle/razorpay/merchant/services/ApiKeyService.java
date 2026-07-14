package com.codingshuttle.razorpay.merchant.services;

import com.codingshuttle.razorpay.merchant.dto.request.CreateApiKeyRequest;
import com.codingshuttle.razorpay.merchant.dto.response.ApiKeyCreateResponse;
import com.codingshuttle.razorpay.merchant.dto.response.ApiKeyResponse;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service

public interface ApiKeyService {
    ApiKeyCreateResponse createApiKey(UUID merchantid, @Valid CreateApiKeyRequest request);

    List<ApiKeyResponse> listByMerchant(UUID merchantid);

    void revoke(UUID merchantid, UUID keyId);

    ApiKeyCreateResponse rotateKey(UUID merchantId, UUID keyId);
}

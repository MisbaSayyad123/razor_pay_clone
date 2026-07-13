package com.codingshuttle.razorpay.merchant.services;

import com.codingshuttle.razorpay.merchant.dto.request.CreateApiKeyRequest;
import com.codingshuttle.razorpay.merchant.dto.response.ApiKeyCreateResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service

public interface ApiKeyService {
    ApiKeyCreateResponse createApiKey(UUID merchantid, @Valid CreateApiKeyRequest request);
}

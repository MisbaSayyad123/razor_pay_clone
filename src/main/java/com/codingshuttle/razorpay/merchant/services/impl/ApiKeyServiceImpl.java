package com.codingshuttle.razorpay.merchant.services.impl;

import com.codingshuttle.razorpay.common.exception.ResourceNotFoundException;
import com.codingshuttle.razorpay.merchant.dto.request.CreateApiKeyRequest;
import com.codingshuttle.razorpay.merchant.dto.response.ApiKeyCreateResponse;
import com.codingshuttle.razorpay.merchant.entity.ApiKey;
import com.codingshuttle.razorpay.merchant.entity.Merchant;
import com.codingshuttle.razorpay.merchant.repository.ApiKeyRepository;
import com.codingshuttle.razorpay.merchant.repository.MerchantRepository;
import com.codingshuttle.razorpay.merchant.services.ApiKeyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j

public class ApiKeyServiceImpl implements ApiKeyService {

    private final MerchantRepository merchantRepository;
    private final ApiKeyRepository apiKeyRepository;

    @Override
    public ApiKeyCreateResponse createApiKey(UUID merchantid, CreateApiKeyRequest request) {
        Merchant merchant= merchantRepository.findById(merchantid)
                .orElseThrow(()->new ResourceNotFoundException("MERCHANT" , merchantid.toString()));

        String keyId="rzp_"+request.environment().name().toUpperCase()+"big_randam_string";
        String rawSecrete="big_randam_string";

        ApiKey apiKey=ApiKey.builder()
                .merchant(merchant)
                .keyId(keyId)
                .keySecreteHash(rawSecrete)
                .environment(request.environment())
                .build();

        apiKey=  apiKeyRepository.save(apiKey);

        return new ApiKeyCreateResponse(apiKey.getId(),
                apiKey.getKeyId(),
                apiKey.getKeySecreteHash(),
                apiKey.getEnvironment());

    }
}

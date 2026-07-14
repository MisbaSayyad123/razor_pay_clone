package com.codingshuttle.razorpay.merchant.services.impl;

import com.codingshuttle.razorpay.common.exception.ResourceNotFoundException;
import com.codingshuttle.razorpay.common.util.RandomizerUtil;
import com.codingshuttle.razorpay.merchant.dto.request.CreateApiKeyRequest;
import com.codingshuttle.razorpay.merchant.dto.response.ApiKeyCreateResponse;
import com.codingshuttle.razorpay.merchant.dto.response.ApiKeyResponse;
import com.codingshuttle.razorpay.merchant.entity.ApiKey;
import com.codingshuttle.razorpay.merchant.entity.Merchant;
import com.codingshuttle.razorpay.merchant.repository.ApiKeyRepository;
import com.codingshuttle.razorpay.merchant.repository.MerchantRepository;
import com.codingshuttle.razorpay.merchant.services.ApiKeyService;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j

public class ApiKeyServiceImpl implements ApiKeyService {

    private final MerchantRepository merchantRepository;
    private final ApiKeyRepository apiKeyRepository;

    @Override
    @Transactional
    public ApiKeyCreateResponse createApiKey(UUID merchantid, CreateApiKeyRequest request) {
        Merchant merchant= merchantRepository.findById(merchantid)
                .orElseThrow(()->new ResourceNotFoundException("MERCHANT" , merchantid.toString()));

        String keyId = "rzp_"+request.environment().name().toLowerCase()+"_"+RandomizerUtil.randomBase64(24);
        String rawSecrete = RandomizerUtil.randomBase64(40);

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

    @Override
    public List<ApiKeyResponse> listByMerchant(UUID merchantid) {

        return apiKeyRepository.findByMerchant_Id(merchantid).stream()
                .map(apiKey -> new ApiKeyResponse(apiKey.getId(),
                        apiKey.getKeyId(),
                        apiKey.getEnvironment(),
                        apiKey.isEnabled(),
                        apiKey.getLastUsedAt(),
                        null))
                .toList();
    }

    @Override
    public void revoke(UUID merchantId, UUID keyId) {

        ApiKey apiKey=apiKeyRepository.findByMerchant_IdAndKeyId(merchantId,keyId);

        apiKey.setEnabled(false);
        apiKeyRepository.save(apiKey);
    }

    @Override
    public @Nullable ApiKeyCreateResponse rotateKey(UUID merchantId, UUID keyId) {

        ApiKey apiKey=apiKeyRepository.findByMerchant_IdAndKeyId(merchantId,keyId);

        String rawSecrete = RandomizerUtil.randomBase64(40);

        apiKey.setPreviousKeySecreteHash(apiKey.getKeySecreteHash());
        apiKey.setKeySecreteHash(rawSecrete);
        apiKey.setRotatedAt(LocalDateTime.now());
        apiKey.setGracePeriodExpiresAt(LocalDateTime.now().plusHours(24));

        apiKey=apiKeyRepository.save(apiKey);

        return new ApiKeyCreateResponse(apiKey.getId(),
                apiKey.getKeyId(),
                rawSecrete,
                apiKey.getEnvironment());
    }
}

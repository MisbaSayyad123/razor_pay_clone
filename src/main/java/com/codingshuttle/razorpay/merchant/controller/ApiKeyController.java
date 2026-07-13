package com.codingshuttle.razorpay.merchant.controller;

import com.codingshuttle.razorpay.merchant.dto.request.CreateApiKeyRequest;
import com.codingshuttle.razorpay.merchant.dto.response.ApiKeyCreateResponse;
import com.codingshuttle.razorpay.merchant.services.ApiKeyService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/v1/merchants/{merchantid}/api-keys")

public class ApiKeyController {

    private final ApiKeyService apiKeyService;

    public ApiKeyController(ApiKeyService apiKeyService) {
        this.apiKeyService = apiKeyService;
    }

    @PostMapping()
    public ResponseEntity<ApiKeyCreateResponse> createApiKey(@PathVariable UUID merchantid,
                                                             @RequestBody @Valid CreateApiKeyRequest request){

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(apiKeyService.createApiKey(merchantid,request));
    }
}

package com.codingshuttle.razorpay.merchant.services;

import com.codingshuttle.razorpay.merchant.dto.request.MerchantSignRequest;
import com.codingshuttle.razorpay.merchant.dto.response.MerchantResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service

public interface AuthService {

    public MerchantResponse signUp(MerchantSignRequest request);
}

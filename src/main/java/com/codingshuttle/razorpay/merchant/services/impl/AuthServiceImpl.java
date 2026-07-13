package com.codingshuttle.razorpay.merchant.services.impl;

import com.codingshuttle.razorpay.common.eums.MerchantStatus;
import com.codingshuttle.razorpay.common.eums.UserRole;
import com.codingshuttle.razorpay.common.exception.DuplicateResourceException;
import com.codingshuttle.razorpay.merchant.dto.request.MerchantSignRequest;
import com.codingshuttle.razorpay.merchant.dto.response.MerchantResponse;
import com.codingshuttle.razorpay.merchant.entity.AppUser;
import com.codingshuttle.razorpay.merchant.entity.Merchant;
import com.codingshuttle.razorpay.merchant.repository.AppUserRepository;
import com.codingshuttle.razorpay.merchant.repository.MerchantRepository;
import com.codingshuttle.razorpay.merchant.services.AuthService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {

    private final AppUserRepository appUserRepository;
    private final MerchantRepository merchantRepository;

    @Transactional
    @Override
    public MerchantResponse signUp(MerchantSignRequest request) {

        if(merchantRepository.existsByEmail(request.email())){
            throw new DuplicateResourceException("DUPLICATE_MERCHANT_EMAIL","Merchant with email id already exist: " +request.email());
        }

        Merchant merchant= Merchant.builder()
                .businessName(request.businessName())
                .businessType(request.businessType())
                .name(request.name())
                .email(request.email())
                .merchantStatus(MerchantStatus.PENDING_KYC)
                .build();

        merchant = merchantRepository.save(merchant);

        AppUser appUser =AppUser.builder()
                .email(request.email())
                .merchant(merchant)
                .userRole(UserRole.OWNER)
                .passwordHash(request.password())
                .build();

        appUserRepository.save(appUser);

        return new MerchantResponse(merchant.getId(),
                merchant.getName(),
                merchant.getEmail(),
                merchant.getBusinessName(),
                merchant.getBusinessType(),
                merchant.getMerchantStatus());
    }
}

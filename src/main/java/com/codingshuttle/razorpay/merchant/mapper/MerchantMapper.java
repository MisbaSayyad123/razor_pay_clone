package com.codingshuttle.razorpay.merchant.mapper;

import com.codingshuttle.razorpay.merchant.dto.request.MerchantSignRequest;
import com.codingshuttle.razorpay.merchant.dto.response.MerchantResponse;
import com.codingshuttle.razorpay.merchant.entity.Merchant;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface MerchantMapper {

    Merchant toEntityFromSignupRequest(MerchantSignRequest merchantSignRequest);

    MerchantResponse toResponse(Merchant merchant);
}

package com.codingshuttle.razorpay.merchant.entity;

import com.codingshuttle.razorpay.common.eums.BusinessType;
import com.codingshuttle.razorpay.common.eums.MerchantStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name= "merchant")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Merchant {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, length = 200)
    private String name;

    @Column(unique = true, nullable = false)
    private  String email;

    @Column(length = 20)
    private String contactNumber;

    @Column(length = 50)
    @Enumerated(value = EnumType.STRING)
    private BusinessType businessType;

    @Column(length = 100)
    private String businessName;

    @Column(length = 100)
    private String websiteUrl;

    @Column(nullable = false, length = 100)
    @Enumerated(value = EnumType.STRING)
    private MerchantStatus merchantStatus=MerchantStatus.PENDING_KYC;

    @Column(length = 20)
    private String gstId;

    @Column(length = 20)
    private String panId;

    @Column(length = 20)
    private String settlementBankAccount;

    @Column(length = 20)
    private String settlementBankIfsc;

    @Column(length = 50)
    private String settlementBankAccountHolderName;

}

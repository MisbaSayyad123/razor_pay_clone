package com.codingshuttle.razorpay.merchant.entity;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name="merchant_webhoot_config")
public class MerchantWebhookConfig {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(nullable = false,name="mercahnt_id")
    private Merchant merchant;

    @Column(nullable = false,length = 500)
    private String targetUrl;

    @Column(length = 255)
    private String webhookSecreteHash;

    @Column(nullable = false)
    private boolean enabled=true;

    @Column(length = 255)
    private  String eventType;
}

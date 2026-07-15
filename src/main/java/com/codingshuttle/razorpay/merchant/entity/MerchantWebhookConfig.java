package com.codingshuttle.razorpay.merchant.entity;

import com.codingshuttle.razorpay.common.entity.BaseEntity;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name="merchant_webhoot_config",
        indexes = {
                @Index(name="idx_webhook_merchant_id", columnList = "merchant_id, enabled")
        })

public class MerchantWebhookConfig extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(nullable = false,name="merchant_id")
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

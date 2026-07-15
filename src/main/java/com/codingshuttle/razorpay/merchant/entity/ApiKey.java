package com.codingshuttle.razorpay.merchant.entity;

import com.codingshuttle.razorpay.common.entity.BaseEntity;
import com.codingshuttle.razorpay.common.eums.Environment;
import jakarta.persistence.*;
import lombok.*;

import javax.xml.crypto.dsig.spec.XSLTTransformParameterSpec;
import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "api_key",
        indexes = {
                @Index(name="idx_api_key_merchant_env", columnList = "merchant_id,environment,enabled")
        })
@Getter
@Setter

public class ApiKey extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="merchant_id", nullable = false)
    private Merchant merchant;

    @Column(nullable = false, length = 50,unique = true)
    private String keyId;

    @Column(nullable = false, length = 100)
    private String keySecreteHash;

    @Column( length = 100)
    private String previousKeySecreteHash;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private Environment environment;

    @Column(nullable = false)
    @Builder.Default
    private boolean enabled =true;


    private LocalDateTime lastUsedAt;

    private LocalDateTime rotatedAt;

    private LocalDateTime gracePeriodExpiresAt;
}

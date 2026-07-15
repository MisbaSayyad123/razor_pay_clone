package com.codingshuttle.razorpay.vault.entity;

import com.codingshuttle.razorpay.common.entity.BaseEntity;
import jakarta.persistence.*;
import org.antlr.v4.runtime.Token;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "card_token")
public class CardToken extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(unique = true,length = 50,nullable = false)
    private String token;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(nullable = false, name="vault_card_id")
    private VaultCard vaultCard;

    @Column(nullable = false)
    private UUID customer;

    @Column(nullable = false)
    private  UUID merchant;

    private LocalDateTime revokedAt;
}

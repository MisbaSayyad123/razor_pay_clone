package com.codingshuttle.razorpay.merchant.entity;

import com.codingshuttle.razorpay.common.eums.UserRole;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name="app_user")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="merchnat_id")
    private Merchant merchant;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String passwordHash;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private UserRole userRole;
}

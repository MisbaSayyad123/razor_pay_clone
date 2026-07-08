package com.codingshuttle.razorpay.operations;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;

import java.util.UUID;

@Embeddable
public class SettlementPaymentId {
    private UUID settlemetId;
    private UUID paymentId;
}

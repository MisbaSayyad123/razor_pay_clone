package com.codingshuttle.razorpay.operations;

import com.codingshuttle.razorpay.common.entity.Money;
import com.codingshuttle.razorpay.common.eums.SettlementStatus;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name="settlement")
public class Settlement {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private UUID merchantId;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="amountUnits", column = @Column(name = "gross_amount_unit" ,nullable = false)),
            @AttributeOverride(name="currency",column = @Column(name="gross_amount_currency" , nullable = false))
    })
    private Money grossAmount;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="amountUnits", column = @Column(name = "refund_amount_unit" ,nullable = false)),
            @AttributeOverride(name="currency",column = @Column(name="refund_amount_currency" , nullable = false))
    })
    private Money refundAmount;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="amountUnits", column = @Column(name = "gst_amount_unit" ,nullable = false)),
            @AttributeOverride(name="currency",column = @Column(name="gst_amount_currency" , nullable = false))
    })
    private Money gstAmount;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="amountUnits", column = @Column(name = "fee_amount_unit" ,nullable = false)),
            @AttributeOverride(name="currency",column = @Column(name="fee_amount_currency" , nullable = false))
    })
    private Money feeAmount;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="amountUnits", column = @Column(name = "net_amount_unit" ,nullable = false)),
            @AttributeOverride(name="currency",column = @Column(name="net_amount_currency" , nullable = false))
    })
    private Money netAmount;

    @Column(nullable = false,length = 20)
    private String bankReference;

    private LocalDateTime processedAt;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false,length = 20)
    private SettlementStatus settlementStatus;
}

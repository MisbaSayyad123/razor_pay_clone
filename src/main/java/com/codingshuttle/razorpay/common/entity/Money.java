package com.codingshuttle.razorpay.common.entity;

import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode

public class Money {

    private int amountUnits;
    private String currency;

    private Money(int amountUnits, String currency)
    {
        this.currency=currency;
        this.amountUnits=amountUnits;
    }

    private static Money of(int amountUnits, String currency)
    {
        return  new Money(amountUnits,currency);
    }

    private static Money inr(int amountUnits, String currency)
    {
        return  new Money(amountUnits,"INR");
    }


    public Money add(Money other)
    {
        if(this.currency.equals(other.currency))
        {
            throw new IllegalArgumentException("Cannot add maoney with different currency");
        }
        return new Money(this.amountUnits + other.amountUnits, this.currency);
    }

    public Money subtract(Money other)
    {
        if(this.currency.equals(other.currency))
        {
            throw new IllegalArgumentException("Cannot add maoney with different currency");
        }
        return new Money(this.amountUnits - other.amountUnits, this.currency);
    }
}

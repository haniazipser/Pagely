package com.example.Sklep_z_ksiazkami.Model.entity;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Currency;


@Embeddable
@Getter
public class Money  implements Serializable {

    private BigDecimal value;
    protected Money(){}

    public Money(BigDecimal v) {
        this.value = v;
    }

    public Money(double v) {
        this(new BigDecimal(v));
    }

    public static Money zero() {
        return new Money(BigDecimal.ZERO);
    }
    public Money add(Money other) {
        return new Money(this.value.add(other.value));
    }

    public Money subtract(Money other) {
        return new Money(this.value.subtract(other.value));
    }

    public Money multiply(BigDecimal multiplier) {
        return new Money(this.value.multiply(multiplier));
    }


}
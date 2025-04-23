package com.example.Sklep_z_ksiazkami.Model.entity;

import jakarta.persistence.Embeddable;

@SuppressWarnings("serial")
@Embeddable
public class Money{
   /* public static final Currency DEFAULT_
    CURRENCY = Currency.getInstance("EUR");
    public static final Money ZERO = new
            Money(BigDecimal.ZERO, DEFAULT_CURRENCY);
    private BigDecimal value;
    private String currencyCode;
    public Money(double value, Currency
            currency) {
        this(new BigDecimal(value), currency.
                getCurrencyCode());
    }
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Money) {
            Money money = (Money) obj;
            return compatibleCurrency(money) &&
                    value.equals(money.value);
        }
        return false;
    }
    public Money multiplyBy(double multiplier)
    {
        return multiplyBy(new
                BigDecimal(multiplier));
    }
    public Money add(Money money) {
        if (!compatibleCurrency(money)) {
            throw new (IllegalArgumentException("Currency mismatch");
        }
        return new Money(value.add(money.value),
                determineCurrencyCode(money));
    }
    public String getCurrencyCode() {
        return currencyCode;
    }
    public boolean greaterThan(Money other) {
        return value.compareTo(other.value) > 0;
    }
    @Override
    public String toString() {
        return String.format("%0$.2f %s", value,
                getCurrency().getSymbol());*/}
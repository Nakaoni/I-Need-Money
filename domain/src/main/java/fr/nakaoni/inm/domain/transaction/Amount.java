package fr.nakaoni.inm.domain.transaction;

import jakarta.persistence.Embeddable;

import java.util.Currency;
import java.util.Objects;

@Embeddable
public final class Amount {
    private int value;
    private Currency currency;

    public Amount() {}

    public Amount(int value, Currency currency) {
        if (value < 0) {
            throw new IllegalArgumentException("Amount must be non-negative");
        }

        if (currency == null) {
            throw new IllegalArgumentException("Currency cannot be null");
        }

        this.value = value;
        this.currency = currency;
    }

    public int value() {
        return value;
    }

    public Currency currency() {
        return currency;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Amount money = (Amount) o;
        return value == money.value && currency.equals(money.currency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, currency);
    }
}

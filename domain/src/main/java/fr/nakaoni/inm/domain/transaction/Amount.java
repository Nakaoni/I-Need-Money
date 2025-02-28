package fr.nakaoni.inm.domain.transaction;

import java.util.Currency;
import java.util.Objects;

public final class Amount {
    private final int value;
    private final Currency currency;

    public Amount(int amount, Currency currency) {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount must be non-negative");
        }

        if (currency == null) {
            throw new IllegalArgumentException("Currency cannot be null");
        }

        this.value = amount;
        this.currency = currency;
    }

    public int amount() {
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

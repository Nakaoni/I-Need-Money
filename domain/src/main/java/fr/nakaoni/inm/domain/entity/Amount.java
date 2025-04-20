package fr.nakaoni.inm.domain.entity;

import java.util.Currency;

public record Amount(int value, Currency currency) {
    public Amount {
        if (value < 0) {
            throw new IllegalArgumentException("Amount must be non-negative");
        }

        if (currency == null) {
            throw new IllegalArgumentException("Currency cannot be null");
        }

    }
}

package fr.nakaoni.inm.domain.transaction;

import org.junit.jupiter.api.Test;

import java.util.Currency;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AmountTest {
    @Test
    void testConstructor() {
        int expectedAmount = 1000;
        Currency expectedCurrency = Currency.getInstance("EUR");
        Amount m = new Amount(expectedAmount, expectedCurrency);

        assertEquals(expectedAmount, m.value());
        assertEquals(expectedCurrency, m.currency());
    }

    @Test
    void testConstructorWithNegativeAmount() {
        int expectedAmount = -1;
        Currency expectedCurrency = Currency.getInstance("EUR");

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Amount(expectedAmount, expectedCurrency));

        assertEquals("Amount must be non-negative", exception.getMessage());
    }

    @Test
    void testConstructorWithNullCurrency() {
        int expectedAmount = 1000;
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Amount(expectedAmount, null));

        assertEquals("Currency cannot be null", exception.getMessage());
    }
}
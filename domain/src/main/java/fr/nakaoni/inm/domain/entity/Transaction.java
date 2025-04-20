package fr.nakaoni.inm.domain.entity;

import java.time.Instant;

public record Transaction(Long id, Category category, String payee, Account account, String comment,
                          fr.nakaoni.inm.domain.entity.Transaction.State state,
                          fr.nakaoni.inm.domain.entity.Transaction.Type type, Amount amount, Instant createdAt,
                          Instant updatedAt) {
    public enum State {
        PENDING,
        CLEARED,
    }

    public enum Type {
        INCOME,
        EXPENSE,
    }
}

package fr.nakaoni.inm.domain.transaction;

import fr.nakaoni.inm.domain.account.Account;

import java.time.Instant;

public class Transaction {
    private Long id;
    private Category category;
    private String payee;
    private Account account;
    private String comment;
    private State state;
    private Type type;
    private Amount amount;
    private Instant createdAt;
    private Instant updatedAt;

    public enum State {
        PENDING,
        CLEARED,
    }

    public enum Type {
        INCOME,
        EXPENSE,
    }
}

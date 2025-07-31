package fr.nakaoni.inm.domain.entity;

import java.time.Instant;
import java.util.Objects;

public final class Transaction {
    private final Long id;
    private final Account account;
    private final Instant createdAt;
    private Category category;
    private String payee;
    private String description;
    private State state;
    private Type type;
    private Amount amount;
    private Instant updatedAt;

    public Transaction(Long id, Category category, String payee, Account account, String description,
                       State state,
                       Type type, Amount amount, Instant createdAt) {
        this.id = id;
        this.category = category;
        this.payee = payee;
        this.account = account;
        this.description = description;
        this.state = state;
        this.type = type;
        this.amount = amount;
        this.createdAt = createdAt;
        this.updatedAt = createdAt;
    }

    public Long id() {
        return id;
    }

    public Category category() {
        return category;
    }

    public void changeCategory(Category category) {
        this.category = category;
        this.updatedAt = Instant.now();
    }

    public String payee() {
        return payee;
    }

    public void definePayee(String payee) {
        this.payee = payee;
        this.updatedAt = Instant.now();
    }

    public Account account() {
        return account;
    }

    public String description() {
        return description;
    }

    public void describe(String description) {
        this.description = description;
        this.updatedAt = Instant.now();
    }

    public State state() {
        return state;
    }

    public void updateState(State state) {
        this.state = state;
        this.updatedAt = Instant.now();
    }

    public Type type() {
        return type;
    }

    public void defineType(Type type) {
        this.type = type;
        this.updatedAt = Instant.now();
    }

    public Amount amount() {
        return amount;
    }

    public void setAmount(Amount amount) {
        this.amount = amount;
        this.updatedAt = Instant.now();
    }

    public Instant createdAt() {
        return createdAt;
    }

    public Instant updatedAt() {
        return updatedAt;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Transaction) obj;
        return Objects.equals(this.id, that.id) &&
                Objects.equals(this.category, that.category) &&
                Objects.equals(this.payee, that.payee) &&
                Objects.equals(this.account, that.account) &&
                Objects.equals(this.description, that.description) &&
                Objects.equals(this.state, that.state) &&
                Objects.equals(this.type, that.type) &&
                Objects.equals(this.amount, that.amount) &&
                Objects.equals(this.createdAt, that.createdAt) &&
                Objects.equals(this.updatedAt, that.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, category, payee, account, description, state, type, amount, createdAt, updatedAt);
    }

    @Override
    public String toString() {
        return "Transaction[" +
                "id=" + id + ", " +
                "category=" + category + ", " +
                "payee=" + payee + ", " +
                "account=" + account + ", " +
                "description=" + description + ", " +
                "state=" + state + ", " +
                "type=" + type + ", " +
                "amount=" + amount + ", " +
                "createdAt=" + createdAt + ", " +
                "updatedAt=" + updatedAt + ']';
    }

    public enum State {
        PENDING,
        CLEARED,
    }

    public enum Type {
        INCOME,
        EXPENSE,
    }
}

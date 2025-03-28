package fr.nakaoni.inm.domain.transaction;

import fr.nakaoni.inm.domain.account.Account;
import jakarta.persistence.*;

import java.time.Instant;
import java.util.Objects;

@Entity(name = "transaction")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(targetEntity = Category.class)
    private Category category;

    private String payee;

    @ManyToOne(targetEntity = Account.class)
    private Account account;

    @Lob
    private String comment;

    private State state;

    private Type type;

    @Embedded
    private Amount amount;

    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "updated_at")
    private Instant updatedAt;

    public enum State {
        PENDING,
        CLEARED,
    }

    public enum Type {
        INCOME,
        EXPENSE,
    }

    public Transaction() {}

    public Transaction(Category category, String payee, Account account, Type type, Amount amount, Instant createdAt) {
        this.category = category;
        this.payee = payee;
        this.account = account;
        this.type = type;
        this.amount = amount;
        this.createdAt = createdAt;
        this.updatedAt = createdAt;
        this.comment = "";
        this.state = State.PENDING;
    }

    public Long id() {
        return id;
    }

    public Category category() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String payee() {
        return payee;
    }

    public void setPayee(String payee) {
        this.payee = payee;
    }

    public Account account() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String comment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public State state() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Type type() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Amount amount() {
        return amount;
    }

    public void setAmount(Amount amount) {
        this.amount = amount;
    }

    public Instant createdAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant updatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return Objects.equals(id, that.id)
                && Objects.equals(category, that.category)
                && Objects.equals(payee, that.payee)
                && Objects.equals(account, that.account)
                && Objects.equals(comment, that.comment)
                && state == that.state
                && type == that.type
                && Objects.equals(amount, that.amount)
                && Objects.equals(createdAt, that.createdAt)
                && Objects.equals(updatedAt, that.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, category, payee, account, comment, state, type, amount, createdAt, updatedAt);
    }
}

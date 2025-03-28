package fr.nakaoni.inm.api.transaction;

import com.fasterxml.jackson.annotation.JsonProperty;
import fr.nakaoni.inm.api.account.AccountEntity;
import fr.nakaoni.inm.api.bank.BankEntity;
import fr.nakaoni.inm.domain.account.Account;
import fr.nakaoni.inm.domain.transaction.Amount;
import fr.nakaoni.inm.domain.transaction.Transaction;
import jakarta.persistence.*;

import java.time.Instant;
import java.util.Objects;

@Entity(name = "account")
public class TransactionEntity {
    @Id
    @GeneratedValue()
    @JsonProperty
    private Long id;

    @ManyToOne(targetEntity = AccountEntity.class)
    @JsonProperty
    private AccountEntity account;

    @JsonProperty
    private String payee;

    @JsonProperty
    private String comment;

    @JsonProperty
    private Transaction.State state;

    @JsonProperty
    private Transaction.Type type;

    @JsonProperty
    @Embedded
    private Amount amount;

    @JsonProperty
    private Instant createdAt;

    @JsonProperty
    private Instant updatedAt;

    @JsonProperty
    private CategoryEntity category;

    public TransactionEntity() {}

    public TransactionEntity(AccountEntity account, Amount amount, String payee, Transaction.Type type, Instant createdAt, Instant updatedAt, CategoryEntity category) {
        this.account = account;
        this.amount = amount;
        this.payee = payee;
        this.type = type;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.category = category;
    }

    public TransactionEntity(Long id, AccountEntity account, Amount amount, String payee, Transaction.Type type, Instant createdAt, Instant updatedAt, CategoryEntity category) {
        this.id = id;
        this.account = account;
        this.amount = amount;
        this.payee = payee;
        this.type = type;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.category = category;
    }

    public static TransactionEntity fromDomain(Transaction transaction) {
        return new TransactionEntity(AccountEntity.fromDomain(transaction.account()), transaction.amount(), transaction.payee(), transaction.type(), transaction.createdAt(), transaction.updatedAt(), CategoryEntity.fromDomain(transaction.category()));
    }

    public Transaction toDomain() {
        Transaction transaction = new Transaction(id, category.toDomain(), payee, account.toDomain(), type, amount, createdAt);
        transaction.setUpdatedAt(updatedAt);

        return transaction;
    }

    public Long id() {
        return id;
    }
}

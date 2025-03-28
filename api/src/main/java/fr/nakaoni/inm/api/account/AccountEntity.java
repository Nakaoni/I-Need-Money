package fr.nakaoni.inm.api.account;

import com.fasterxml.jackson.annotation.JsonProperty;
import fr.nakaoni.inm.api.bank.BankEntity;
import fr.nakaoni.inm.domain.account.Account;
import jakarta.persistence.*;

import java.util.Objects;

@Entity(name = "account")
public class AccountEntity {
    @Id
    @GeneratedValue()
    @JsonProperty
    private Long id;

    @Column(unique = true)
    @JsonProperty
    private String name;

    @ManyToOne(targetEntity = BankEntity.class)
    @JsonProperty
    private BankEntity bank;

    @JsonProperty
    private Account.AccountType type;

    public AccountEntity() {}

    public AccountEntity(String name, BankEntity bank, Account.AccountType type) {
        this.name = name;
        this.bank = bank;
        this.type = type;
    }

    public AccountEntity(Long id, String name, BankEntity bank, Account.AccountType type) {
        this.id = id;
        this.name = name;
        this.bank = bank;
        this.type = type;
    }

    public static AccountEntity fromDomain(Account account) {
        return new AccountEntity(account.id(), account.name(), BankEntity.fromDomain(account.bank()), account.type());
    }

    public Account toDomain() {
        return new Account(id, name, bank.toDomain(), type);
    }

    public Long id() {
        return id;
    }

    public String name() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BankEntity bank() {
        return bank;
    }

    public void setBank(BankEntity bank) {
        this.bank = bank;
    }

    public Account.AccountType type() {
        return type;
    }

    public void setType(Account.AccountType type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AccountEntity account)) return false;
        return Objects.equals(name, account.name) && Objects.equals(bank, account.bank) && type == account.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, bank, type);
    }
}

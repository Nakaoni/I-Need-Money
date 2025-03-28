package fr.nakaoni.inm.domain.account;

import fr.nakaoni.inm.domain.bank.Bank;
import jakarta.persistence.*;

import java.util.Objects;

@Entity(name = "account")
public final class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToOne(targetEntity = Bank.class)
    private Bank bank;

    private AccountType type;

    public Account() {}

    public Account(String name, Bank bank, AccountType type) {
        this.name = name;
        this.bank = bank;
        this.type = type;
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

    public Bank bank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    public AccountType type() {
        return type;
    }

    public void setType(AccountType type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(name, account.name()) && Objects.equals(bank, account.bank()) && type == account.type();
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, bank, type);
    }

    public enum AccountType {
        CHECKING,
        SAVING,
    }

}

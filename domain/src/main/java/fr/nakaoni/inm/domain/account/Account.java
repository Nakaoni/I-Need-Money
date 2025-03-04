package fr.nakaoni.inm.domain.account;

import fr.nakaoni.inm.domain.bank.Bank;

import java.util.Objects;

public class Account {
    private final Long id;
    private String name;
    private Bank bank;
    private AccountType type;

    public Account(Long id, String name, Bank bank, AccountType type) {
        this.id = id;
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

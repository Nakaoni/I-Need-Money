package fr.nakaoni.inm.domain.entity;

import java.util.Objects;

public final class Account {
    private final Long id;
    private final Bank bank;
    private String name;
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

    public void rename(String name) {
        this.name = name;
    }

    public Bank bank() {
        return bank;
    }

    public AccountType type() {
        return type;
    }

    public void changeType(AccountType type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Account) obj;
        return Objects.equals(this.id, that.id) &&
                Objects.equals(this.name, that.name) &&
                Objects.equals(this.bank, that.bank) &&
                Objects.equals(this.type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, bank, type);
    }

    @Override
    public String toString() {
        return "Account[" +
                "id=" + id + ", " +
                "name=" + name + ", " +
                "bank=" + bank + ", " +
                "type=" + type + ']';
    }

    public enum AccountType {
        CHECKING,
        SAVING,
    }
}

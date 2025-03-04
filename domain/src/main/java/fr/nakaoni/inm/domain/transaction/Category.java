package fr.nakaoni.inm.domain.transaction;

import fr.nakaoni.inm.domain.account.Account;

import java.util.Objects;

public class Category {
    private final Long id;
    private String name;
    private Account account;

    public Category(Long id, String name, Account account) {
        this.id = id;
        this.name = name;
        this.account = account;
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

    public Account account() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return Objects.equals(name, category.name) && Objects.equals(account, category.account);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, account);
    }
}

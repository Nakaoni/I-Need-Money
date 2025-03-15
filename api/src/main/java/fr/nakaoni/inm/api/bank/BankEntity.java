package fr.nakaoni.inm.api.bank;

import fr.nakaoni.inm.domain.bank.Bank;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.util.Objects;

@Entity
public class BankEntity {
    @Id
    @GeneratedValue()
    private Long id;

    @Column(unique = true)
    private String name;

    public BankEntity() {}

    public BankEntity(String name) {
        this.name = name;
    }

    public BankEntity(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public static BankEntity fromDomain(Bank bank) {
        return new BankEntity(bank.id(), bank.name());
    }

    public Bank toDomain() {
        return new Bank(id, name);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BankEntity that)) return false;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }
}

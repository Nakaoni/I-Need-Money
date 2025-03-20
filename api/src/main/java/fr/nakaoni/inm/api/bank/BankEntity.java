package fr.nakaoni.inm.api.bank;

import com.fasterxml.jackson.annotation.JsonProperty;
import fr.nakaoni.inm.domain.bank.Bank;
import jakarta.persistence.*;

import java.util.Objects;

@Entity(name = "bank")
public class BankEntity {
    @Id
    @GeneratedValue()
    @JsonProperty
    private Long id;

    @Column(unique = true)
    @JsonProperty
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

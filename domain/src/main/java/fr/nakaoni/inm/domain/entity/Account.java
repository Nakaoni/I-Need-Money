package fr.nakaoni.inm.domain.entity;

public record Account(Long id, String name, Bank bank, fr.nakaoni.inm.domain.entity.Account.AccountType type) {
    public enum AccountType {
        CHECKING,
        SAVING,
    }
}

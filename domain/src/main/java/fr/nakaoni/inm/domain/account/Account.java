package fr.nakaoni.inm.domain.account;

import fr.nakaoni.inm.domain.bank.Bank;

public class Account {
    private Long id;
    private String name;
    private Bank bank;
    private AccountType type;

    enum AccountType {
        CHECKING,
        SAVING,
    }
}

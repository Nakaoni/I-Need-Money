package fr.nakaoni.inm.api.account;

import fr.nakaoni.inm.domain.entity.Account;
import fr.nakaoni.inm.domain.entity.Bank;

public record AccountDto(String name, Bank bank, Account.AccountType type) {}
